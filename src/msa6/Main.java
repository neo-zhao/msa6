package msa6;

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
	 */
	public static void main(String[] args) {
		
		//**Set Up Sim**//
		
		int livesLost = 0;
		LinkedList<Bloon> bloonList = new LinkedList<Bloon>();
		double elapsedTime = 0.01;
		
		//create map
		GameMap brickWall = new BrickWallMap();
		
		//add Towers
		brickWall.addTower(new DartMonkey00(new Coordinate(205/700*MiscHelper.getMapLength(), MiscHelper.getMapHeight()/2), MiscHelper.getPriorityFirst()));
		
		//*add Bloons*//
		bloonList.add(new RedBloon(0));
		
		//initializing values for the bloons and finding maxSpawnTime
		for (int i = 0; i < bloonList.size(); i++) {
			int pathNumber = i%brickWall.getPaths().size();
			bloonList.get(i).setPathNumber(pathNumber);
			bloonList.get(i).setPath(brickWall.getPaths().get(pathNumber));
			bloonList.get(i).setPosition(brickWall.getPaths().get(pathNumber).get(0));
		}
		
		//**Run Sim**//
		for (int time = 0; !bloonList.isEmpty() || !brickWall.getBloons().isEmpty(); time += elapsedTime) {
			
			//*Updating Bloons*//
			
			//move old bloons
			for (int j = 0; j < brickWall.getBloons().size(); j++) {
				Bloon b = brickWall.getBloons().get(j);
				b.move(elapsedTime);
				if (brickWall.outOfBounds(b.getPosition())) {
					livesLost += b.getRbeValue();
					brickWall.getBloons().remove(b);
				}
			}
			
			//spawn new bloons
			LinkedList<Bloon> spawnedBloons = new LinkedList<Bloon>();
			for (Bloon b: bloonList) {
				if (b.getSpawnTime() >= time) {
					spawnedBloons.add(b);
				}
			}
			for (Bloon b: spawnedBloons) {
				bloonList.remove(b);
				brickWall.addBloon(b);
			}
			
			//*Updating Projectiles*//
			for (int j = 0; j < brickWall.getProjectiles().size(); j++) {
				Projectile projectile = brickWall.getProjectiles().get(j);
				//move projectiles
				projectile.move(elapsedTime);
				
				//if the projectile comes in contact with a bloon
				for (int k = 0; k < brickWall.getBloons().size(); k++) {
					Bloon bloon = brickWall.getBloons().get(k);
					if (projectile.getPosition().distance(bloon.getPosition()) <= (projectile.getRadius() + bloon.getRadius())) {
						//updating projectiles
						for (Projectile p: projectile.pop(bloon)) {
							brickWall.addProjectile(p);
						}
						if(projectile.getDurability() == 0) {
							brickWall.getProjectiles().remove(projectile);
							j--;
						}
						
						//updating bloons
						brickWall.getBloons().remove(bloon);
						k--;
						for (Bloon b: bloon.pop(projectile).getBloons()) {
							brickWall.addBloon(b);
						}
						for (Projectile p: bloon.pop(projectile).getProjectiles()) {
							brickWall.addProjectile(p);
						}
					}
				}
				
				//check if projectiles are out of bounds
				if (brickWall.outOfBounds(projectile.getPosition())) {
					brickWall.getProjectiles().remove(projectile);
					j--;
				}
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
					}
				} else {
					// if there are no bloons in range, still update tower
					t.update(elapsedTime);
				}
			}
		}
		
		System.out.println("Lives Lost: " + livesLost);
	}
}