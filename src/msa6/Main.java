package msa6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import bloons.RedBloon;
import framework.Bloon;
import framework.Coordinate;
import framework.GameMap;
import framework.MiscHelper;
import framework.Projectile;
import framework.Tower;
import map.BrickWallMap;
import towers.DartMonkey00;

public class Main {
	
	/**
	 * Main function (The Sim is run in here)
	 * @param args not used
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//**Set Up Sim**//
		
		int livesLost = 0;
		LinkedList<Bloon> bloonList = new LinkedList<Bloon>();
		double elapsedTime = 0.001;
		FileWriter eventWriter = new FileWriter(new File ("EventLog.txt"));
		
		//create map
		GameMap brickWall = new BrickWallMap();
		
		//add Towers
		brickWall.addTower(new DartMonkey00(new Coordinate(205.0/700.0*MiscHelper.getMapLength(), MiscHelper.getMapHeight()/2), MiscHelper.getPriorityFirst(), "1"));
		
		//*add Bloons*//
		for (int i = 0; i < 10; i++) {
			bloonList.add(new RedBloon(i, "" + i));
		}
		
		//initializing values for the bloons and finding maxSpawnTime
		for (int i = 0; i < bloonList.size(); i++) {
			int pathNumber = i%brickWall.getPaths().size();
			bloonList.get(i).setPathNumber(pathNumber);
			bloonList.get(i).setPath(brickWall.getPaths().get(pathNumber));
			bloonList.get(i).setPosition(brickWall.getPaths().get(pathNumber).get(0).clone());
		}
		
		//**Run Sim**//
		for (double time = 0; !bloonList.isEmpty() || !brickWall.getBloons().isEmpty(); time += elapsedTime) {
			//eventWriter.write(" ");
			
			//*Updating Bloons*//
			
			//move old bloons
			for (int j = 0; j < brickWall.getBloons().size(); j++) {
				Bloon b = brickWall.getBloons().get(j);
				b.move(elapsedTime);
				//eventWriter.write("\t Bloon " + b.getID() + " moves to " + b.getPosition().toString() + "\n");
				if (brickWall.outOfBounds(b.getPosition())) {
					livesLost += b.getRbeValue();
					brickWall.getBloons().remove(b);
					eventWriter.write("t: " + time + ", Bloon " + b.getID() + " leaves unscathed\n");
				}
			}
			
			//spawn new bloons
			LinkedList<Bloon> spawnedBloons = new LinkedList<Bloon>();
			for (Bloon b: bloonList) {
				if (b.getSpawnTime() <= time) {
					spawnedBloons.add(b);
					eventWriter.write("t: " + time +", Bloon " + b.getID() + " spawns at " + b.getPosition().toString() + "\n");
				}
			}
			for (Bloon b: spawnedBloons) {
				bloonList.remove(b);
				brickWall.addBloon(b);
			}
			
			//*Updating Projectiles and Bloons*//
			LinkedList<Projectile> projectilesToBeRemoved = new LinkedList<Projectile>();
			LinkedList<Projectile> projectilesToBeAdded = new LinkedList<Projectile>();
			for (Projectile p: brickWall.getProjectiles()) {
				//move projectiles
				p.move(elapsedTime);
				//eventWriter.write("\t Projectile " + p.getID() + " moves to " + p.getPosition().toString() + "\n");
				
				//finding bloons that the Projectile has hit
				LinkedList<Bloon> hitBloons = new LinkedList<Bloon>();
				for (Bloon b: brickWall.getBloons()) {
					if (p.getPosition().distance(b.getPosition()) <= (p.getRadius() + b.getRadius())) {
						hitBloons.add(b);
						eventWriter.write("t: " + time + ", Projectile " + p.getID() + " hits " + b.getID() + "\n");
					}
				}
				
				//"popping" the bloons
				for (Bloon b: hitBloons) {
					//updating bloons
					brickWall.getBloons().remove(b);
					for (Bloon b1: b.pop(p).getBloons()) {
						brickWall.addBloon(b1);
					}
					for (Projectile p1: b.pop(p).getProjectiles()) {
						brickWall.addProjectile(p1);
					}
					
					//updating projectiles
					for (Projectile p1: p.pop(b)) {
						projectilesToBeAdded.add(p1);
					}
					if(p.getDurability() <= 0) {
						projectilesToBeRemoved.add(p);
						break;
					}
				}
				
				//check if projectiles are out of bounds
				if (brickWall.outOfBounds(p.getPosition())) {
					projectilesToBeRemoved.add(p);	
				}
			}
			//removing and adding the projectiles that need to be removed or added
			for (Projectile p: projectilesToBeRemoved) {
				brickWall.getProjectiles().remove(p);
			}
			for (Projectile p: projectilesToBeAdded) {
				brickWall.getProjectiles().add(p);
			}
			
			//*Updating Bloon Rank*//
			int bloonNumber = brickWall.getBloons().size();
			LinkedList<Bloon> ranked = new LinkedList<Bloon>();

			// Update "STRONG"
			for (int j = 1; j <= bloonNumber; j++) {
				int nextStrongest = 0;
				// find the strongest Bloon (with greatest distanceTraveled if there's a tie for
				// rbe value)
				for (int k = 0; k < brickWall.getBloons().size(); k++) {
					// checking rbe value
					if (brickWall.getBloons().get(k).getRbeValue() > brickWall.getBloons().get(nextStrongest).getRbeValue()) {
						nextStrongest = k;
					}

					// checking distance traveled
					else if (brickWall.getBloons().get(k).getRbeValue() == brickWall.getBloons().get(nextStrongest).getRbeValue()) {
						if (brickWall.getBloons().get(k).getDistanceTraveled() > brickWall.getBloons().get(nextStrongest).getDistanceTraveled()) {
							nextStrongest = k;
						}
					}
				}

				// rank the bloon, then delete it from the map and add it to the list of ranked
				// bloons
				brickWall.getBloons().get(nextStrongest).setRank("STRONG", j);
				ranked.add(brickWall.getBloons().remove(nextStrongest));
			}
			for (Bloon b : ranked) {
				// add the ranked bloons back to the map
				brickWall.addBloon(b);
			}

			// Update "FIRST" and "LAST"
			ranked = new LinkedList<Bloon>();
			for (int j = 1; j <= bloonNumber; j++) {
				int nextFirst = 0;
				// find the first Bloon (with greatest distanceTraveled)
				for (int k = 0; k < brickWall.getBloons().size(); k++) {
					// checking distanceTraveled
					if (brickWall.getBloons().get(k).getDistanceTraveled() > brickWall.getBloons().get(nextFirst).getDistanceTraveled()) {
						nextFirst = k;
					}
				}

				// rank the bloon, then delete it from the map and add it to the list of ranked
				// bloons
				brickWall.getBloons().get(nextFirst).setRank("FIRST", j);
				brickWall.getBloons().get(nextFirst).setRank("LAST", bloonNumber - j + 1);
				ranked.add(brickWall.getBloons().remove(nextFirst));
			}
			for (Bloon b : ranked) {
				// add the ranked bloons back to the map
				brickWall.addBloon(b);
			}
			
			/*Towers Attack*/
			for (Tower t : brickWall.getTowers()) {
				// find bloons within range of the tower
				LinkedList<Bloon> inRange = new LinkedList<Bloon>();
				for (Bloon b : brickWall.getBloons()) {
					if (t.inRange(b)) {
						inRange.add(b);
					}
				}

				// update the tower
				if (!inRange.isEmpty()) {
					// if there are bloons in range
					for (Projectile p : t.update(inRange, elapsedTime)) {
						brickWall.addProjectile(p);
						eventWriter.write("t: " + time + ", Tower " + t.getID() + " summons " + p.getID() + "\n");
					}
				} else {
					// if there are no bloons in range, still update tower
					t.update(elapsedTime);
				}
			}
			//eventWriter.write("------------------------------------------------\n");
		}
		eventWriter.close();
		System.out.println("Lives Lost: " + livesLost);
	}
}