package msa6;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		//setting up the map
		GameMap brickWall = new MapBrickWall();
		LinkedList<LinkedList<Coordinate>> paths = brickWall.getPaths();
	
		//adding towers (create new towers here)
		brickWall.addTower(new DartMonkey00(new Coordinate(169, 259), "FIRST"));
		
		//adding bloons (create new bloons here)
		brickWall.addBloon(new RedBloon(0));
		
		
		//setting up model
		for (int i = 0; i < brickWall.getBloons().size(); i++) {
			brickWall.getBloons().get(i).setPath(paths.get(i % paths.size()));
			brickWall.getBloons().get(i).setPathnumber(i % paths.size());
			brickWall.getBloons().get(i).setPosition(paths.get(i % paths.size()).getFirst());
		}
		LinkedList<Double> eventList = new LinkedList<Double>();
		LinkedList<LinkedList<Double>> distances = new LinkedList<LinkedList<Double>>();
		for (int i = 0; i < brickWall.getPaths().size(); i++) {
			for (Tower t: brickWall.getTowers()) {
				distances.add(t.getPathIntersectionDistance(brickWall.getPaths()).get(i));
			}
		}
		for(Bloon b: brickWall.getBloons()) {
			eventList.add(b.getSpawnTime());
			for (double t: b.getEncounterTimes(distances)) {
				eventList.add(t);
			}
		}
		
		//run simulation
		while (!eventList.isEmpty()) {
			//jump to next event time
			double elapsedTime = eventList.getFirst();
			int indexET = 0;
			for(int i = 0; i < eventList.size(); i++) {
				if(elapsedTime > eventList.get(i)) {
					indexET = i;
				}
			}
			elapsedTime = eventList.remove(indexET);
			int size = eventList.size();
			for(int i = 0; i < size; i++) {
				eventList.add(eventList.getFirst() - elapsedTime);
				eventList.removeFirst();
			}
			
			//move bloons (check if any make it to the end of the map)
			for (Bloon b: brickWall.getBloons()) {
				b.update(elapsedTime);
			}
			
			//update ranks of the bloons
			double distance = -1;
			for (int i = 1; i < brickWall.getBloons().size() + 1; i++) {
				int next = 0;
				for (int j = 0; j < brickWall.getBloons().size(); j++) {
					if (brickWall.getBloons().get(j).getDistanceTraveled() >= distance && brickWall.getBloons().get(j).getDistanceTraveled() < brickWall.getBloons().get(next).getDistanceTraveled()) {
						next = j;
					}
				}
				brickWall.getBloons().get(next).setRank("LAST", i);
				brickWall.getBloons().get(next).setRank("FIRST", brickWall.getBloons().size() + 1 - i);
				distance = brickWall.getBloons().get(next).getDistanceTraveled();
			}
			double rbe = 0;
			for (int i = 1; i < brickWall.getBloons().size() + 1; i++) {
				int next = 0;
				for (int j = 0; j < brickWall.getBloons().size(); j++) {
					if (brickWall.getBloons().get(j).getRbe() >= rbe && brickWall.getBloons().get(j).getRbe() <= brickWall.getBloons().get(next).getRbe()) {
						if (brickWall.getBloons().get(j).getDistanceTraveled() > brickWall.getBloons().get(next).getDistanceTraveled()) {
							next = j;
						}
					}
				}
				brickWall.getBloons().get(next).setRank("STRONG", brickWall.getBloons().size() + 1 - i);
			}
			
			//update location of projectiles and pop bloons (remove used projectiles and popped bloons, add resulting bloons)
			for (Projectile p: brickWall.getProjectiles()) {
				p.update(elapsedTime);
				LinkedList<Bloon> hitBloons = new LinkedList<Bloon>();
				for (Bloon b: brickWall.getBloons()) {
					if (p.getDistance(p.getPosition(), b.getPosition()) <= p.getRadius() + b.getRadius()) {
						hitBloons.add(b);
					}
				}
				for (Bloon b: hitBloons) {
					if (p.getDurability() > 0) {
						for (Bloon b1: b.pop(p)) {
							brickWall.addBloon(b1);
						}
						for (Effect e: p.pop().getEffects()) {
							b.addEffect(e);
						}
						for (Projectile p1: p.pop().getProjectiles()) {
							brickWall.addProjectile(p1);
							for (double t: p1.getIntersectionTimes(paths)) {
								eventList.add(t);
							}
						}
					}
				}
			}
			
			//update towers (towers attack)
			for (Tower t: brickWall.getTowers()) {
				LinkedList<Bloon> inRange = new LinkedList<Bloon>();
				for (Bloon b: brickWall.getBloons()) {
					if (t.getDistance(t.getPosition(), b.getPosition()) <= t.getRange() + b.getRadius()) {
						inRange.add(b);
					}
				}
				if (!inRange.isEmpty()) {
					for (Projectile p: t.update(inRange, elapsedTime)) {
						brickWall.addProjectile(p);
						for (double t1: p.getIntersectionTimes(paths)) {
							eventList.add(t1);
						}
					}
					eventList.add(t.getAttackSpeed());
				}
			}
		}
	}
}
