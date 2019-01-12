package msa6;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
	//GETTING INPUT
		
		GameMap brickWall = new MapBrickWall();
		LinkedList<Bloon> bloonList = new LinkedList<Bloon>();
		int livesLost = 0;
		
		//adding towers (create new towers here; add directly on the map)
		//brickWall.addTower(new DartMonkey00(new Coordinate(169, 259), "FIRST"));
		
		//adding bloons (create new bloons here; add to bloonList)
		bloonList.add(new RedBloon(0));
		
	//SETTING UP THE SIM
		LinkedList<Double> eventList = new LinkedList<Double>();
		
		//filling in missing parameters for the bloons (the starting position, path, and path number); also handles multiple paths (if there are any)
		for (int i = 0; i < bloonList.size(); i++) {
			
			//finding the values
			int pathNumber = i % brickWall.getPaths().size();
			LinkedList<Coordinate> path = brickWall.getPaths().get(pathNumber);
			Coordinate pathStart = path.getFirst();
			
			//sending values to the bloon
			bloonList.get(i).setPathnumber(pathNumber);
			bloonList.get(i).setPath(path);
			bloonList.get(i).setPosition(pathStart);
		}
		
		//adds the spawn times of Bloons into the EventList
		for (Bloon b: bloonList) {
			eventList.add(b.getSpawnTime());
		}
		
	//RUN SIMULATION
		
		while(!eventList.isEmpty()) {
		//jump to next event time (find the smallest double in the eventList and update eventList)
			//finding the smallest and removing it from the eventList
			double elapsedTime = eventList.getFirst();
			for (int i = 0; i < eventList.size(); i++) {
				if(eventList.get(i) < elapsedTime) {
					elapsedTime =eventList.get(i);
				}
			}
			eventList.remove(elapsedTime);
			
			//updating the values in the eventList
			LinkedList<Double> updatedEvents = new LinkedList<Double>();
			for (double t: eventList) {
				updatedEvents.add(t - elapsedTime);
			}
			eventList = updatedEvents;
			
		//move bloons and spawn new ones
			//moves old bloons
			for(Bloon b: brickWall.getBloons()) {
				b.update(elapsedTime);
			}
			
			//Spawns new Bloons
			LinkedList<Bloon> spawnedBloons = new LinkedList<Bloon>();
			for(Bloon b: bloonList) {
				//update spawnTime
				double time = b.getSpawnTime();
				b.setSpawnTime(time - elapsedTime);
				
				//check if it is time for the bloon to be spawned
				if (time - elapsedTime <= 0) {
					//add Bloon to the map
					brickWall.addBloon(b);
					
					//add the time the bloon reaches the end of the map to the eventList
					eventList.add(brickWall.getPathLength(b.getPathNumber()) / b.getMoveSpeed());
					
					//add all the times the bloon enters tower radius to the eventList
					for (Tower t: brickWall.getTowers()) {
						for (double d: t.getPathIntersectionDistance(brickWall.getPaths()).get(b.getPathNumber())) {
							eventList.add(d / b.getMoveSpeed());
						}
					}
					spawnedBloons.add(b);
				}
			}
			
			//removes spawned bloons from the bloonList
			for (Bloon b: spawnedBloons) {
				bloonList.remove(b);
			}
			
		//update projectiles and pop bloons (remove used projectiles and popped bloons, add resulting bloons)
			for (Projectile p: brickWall.getProjectiles()) {
				//moves old projectiles
				p.update(elapsedTime);
				
				//check if any of them came in contact with any bloon
				for (Bloon b: brickWall.getBloons()) {
					if (p.getPosition().getDistance(p.getPosition(), b.getPosition()) < p.getRadius() + b.getRadius()) {
						//pop the bloon (maybe)
						for (Bloon bl: b.pop(p)) {
							//add the resulting bloons from the pop
							brickWall.addBloon(bl);
							
							//add the time the new bloon reaches the end of the map to the eventList
							eventList.add((brickWall.getPathLength(bl.getPathNumber()) - bl.getDistanceTraveled()) / bl.getMoveSpeed());
							
							//add all the times the bloon enters tower radius to the eventList
							for (Tower t: brickWall.getTowers()) {
								for (double d: t.getPathIntersectionDistance(brickWall.getPaths()).get(bl.getPathNumber())) {
									 if (d > bl.getDistanceTraveled()) {
										 eventList.add((d - bl.getDistanceTraveled()) / bl.getMoveSpeed());
										 }
									 }
								}
							}
						
						//update the projectile (remove it if its durability reaches zero)
						p.update(b);
						if(p.getDurability() == 0) {
							brickWall.getProjectiles().remove(p);
							}
						}
					}
				}
		//removes bloons that make it to the end of the map and updates LivesLost
			LinkedList<Bloon> toBeRemoved = new LinkedList<Bloon>();
			//update livesLost
			for (Bloon b: brickWall.getBloons()) {
				if (b.getDistanceTraveled() >= brickWall.getPathLength(b.getPathNumber())) {
					toBeRemoved.add(b);
					livesLost += b.getRbe();
				}
			}
			//remove the bloons that make it to the end of the map
			for (Bloon b: toBeRemoved) {
				brickWall.getBloons().remove(b);
			}
			
		//update ranks of the bloons (check if any make it to the end of the map)
			int bloonNumber = brickWall.getBloons().size();
			LinkedList<Bloon> ranked = new LinkedList<Bloon>();
			
			//Update "STRONG"
			for (int i = 1; i <= bloonNumber; i++) {
				int nextStrongest = 0;
				//find the strongest Bloon (with greatest distanceTraveled if there's a tie for rbe value) 
				for (int j = 0; j < brickWall.getBloons().size(); j++) {
					//checking rbe value
					if (brickWall.getBloons().get(j).getRbe() > brickWall.getBloons().get(nextStrongest).getRbe()) {
						nextStrongest = j;
					}
					
					//checking distance traveled
					else if (brickWall.getBloons().get(j).getRbe() == brickWall.getBloons().get(nextStrongest).getRbe()) {
						if (brickWall.getBloons().get(j).getDistanceTraveled() > brickWall.getBloons().get(nextStrongest).getDistanceTraveled()) {
							nextStrongest = j;
						}
					}
				}
				
				//rank the bloon, then delete it from the map and add it to the list of ranked bloons
				brickWall.getBloons().get(nextStrongest).setRank("STRONG", i);
				ranked.add(brickWall.getBloons().remove(nextStrongest));
			}
			for (Bloon b: ranked) {
				//add the ranked bloons back to the map
				brickWall.addBloon(b);
			}
			
			//Update "FIRST" and "LAST"
			ranked = new LinkedList<Bloon>();
			for (int i = 1; i <= bloonNumber; i++) {
				int nextFirst = 0;
				//find the first Bloon (with greatest distanceTraveled) 
				for (int j = 0; j < brickWall.getBloons().size(); j++) {
					//checking distanceTraveled
					if (brickWall.getBloons().get(j).getDistanceTraveled() > brickWall.getBloons().get(nextFirst).getDistanceTraveled()) {
						nextFirst = j;
						}
					}
	
				//rank the bloon, then delete it from the map and add it to the list of ranked bloons
				brickWall.getBloons().get(nextFirst).setRank("FIRST", i);
				brickWall.getBloons().get(nextFirst).setRank("LAST", bloonNumber - i + 1);
				ranked.add(brickWall.getBloons().remove(nextFirst));
			}
			for (Bloon b: ranked) {
				//add the ranked bloons back to the map
				brickWall.addBloon(b);
			}
			
		//towers attack
			LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
			for (Tower t: brickWall.getTowers()) {
				//find bloons within range of the tower
				LinkedList<Bloon> inRange = new LinkedList<Bloon>();
				for (Bloon b: brickWall.getBloons()) {
					if (b.inRange(t)) {
						inRange.add(b);
					}
				}
				
				//update the tower 
				if (!inRange.isEmpty()) {
					//if there are bloons in range
					int originalSize = projectiles.size();
					for (Projectile p: t.update(inRange, elapsedTime)) {
						brickWall.addProjectile(p);
						
						//add the event times for when the projectile intersects the paths on the map
						for (Double time: p.getIntersectionTimes(brickWall.getPaths())) {
							eventList.add(time);
						}
					}
					
					//add event time for the when the tower is ready to fire again if it fired
					if (projectiles.size() > originalSize) {
						eventList.add(t.getNextAttack());
					}
				}
				else {
					//if there are no bloons in range, still update tower
					t.update(elapsedTime);
				}
			}
		}
		//display livesLost
		System.out.println("Lives Lost: " + livesLost);
	}
}
