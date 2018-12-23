package msa6;

import java.util.LinkedList;

public class Map {
	//variable declarations
	int livesLost;
	LinkedList<Bloon> bloons;
	LinkedList<Projectile> projectiles;
	LinkedList<Tower> towers;
	LinkedList<Coordinate> path;
	/**
	 * constructor
	 */
	public Map(LinkedList<Coordinate> p) {
		livesLost = 0;
		bloons = new LinkedList<Bloon>();
		projectiles = new LinkedList<Projectile>();
		towers = new LinkedList<Tower>();
		path = p;
	}
	//assuming map is 37 (horizontal length) by 27 (vertical height) (based off the diagram)
	
	/**
	 * path
	 * <p>This function takes the current position of the bloon, the speed of the bloon, and the change in time as input and returns the new position of the bloon based off of these inputs</p>
	 * @param p the current position of the bloon
	 * @param s the speed of the bloon (units/second)
	 * @param t the change in time (s)
	 * @return the new position of the bloon based off of the inputs
	 */
	public double[] path(double[] p, int s, double t) {
		double[] rePosition = p;
		
		//part 1: entrance to the map to the first turn
		if (p[0] < 6 && p[1] == 14) {
			rePosition[0] += s*t;
			rePosition = pathOvershootHandler(rePosition, 1);
		}
		//part 2: where the bloons turn and start moving up
		else if (p[0] == 6 && p[1] > 13) {
			rePosition[1] += s*t;
			rePosition = pathOvershootHandler(rePosition, 2);
		}
		//part 3: where the bloons move to the right at the top of the map
		else if (p[0] < 33 && p[1] == 23) {
			rePosition[0] += s*t;
			rePosition = pathOvershootHandler(rePosition, 3);
		}
		//part 4: where the bloons go down at the top right section of the map
		else if (p[0] == 33 && p[1] > 16) {
			rePosition[1] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 4);
		}
		//part 5: the tiny piece where the bloons go left at the top right of the map
		else if (p[0] > 32 && p[1] == 16) {
			rePosition[0] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 5);
		}
		//part 6: where the bloons go up at the top right corner of the map
		else if (p[0] == 32 && p[1] < 22) {
			rePosition[1] += s*t;
			rePosition = pathOvershootHandler(rePosition, 6);
		}
		//part 7: where the bloons go left across the top of the map
		else if (p[0] > 7 && p[1] == 22) {
			rePosition[0] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 7);
		}
		//part 8: where the bloons go down on the left side of the map
		else if (p[0] == 7 && p[1] > 5) {
			rePosition[1] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 8);
		}
		//part 9: where the bloons go right across the bottom of the map
		else if (p[0] < 32 && p[1] == 5) {
			rePosition[0] += s*t;
			rePosition = pathOvershootHandler(rePosition, 9);
		}
		//part 10: where the bloons go up at the lower right corner of the map
		else if (p[0] == 32 && p[1] < 11) {
			rePosition[1] += s*t;
			rePosition = pathOvershootHandler(rePosition, 10);
		}
		//part 11: the tiny piece where the bloons go right at the lower right corner of the map
		else if (p[0] < 33 && p[1] == 11) {
			rePosition[0] += s*t;
			rePosition = pathOvershootHandler(rePosition, 11);
		}
		//part 12: where the bloons go down at the lower right corner of the map
		else if (p[0] == 33 && p[1] > 4) {
			rePosition[1] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 12);
		}
		//part 13: where the bloons go left across the bottom of the map
		else if (p[0] > 6 && p[1] == 4) {
			rePosition[0] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 13);
		}
		//part 14: where the bloons go up at the bottom left of the map
		else if (p[0] == 6 && p[1] < 13) {
			rePosition[1] += s*t;
			rePosition = pathOvershootHandler(rePosition, 14);
		}
		//part 15: where the bloons go left and leave the map
		else if (p[0] > 0 && p[1] == 13) {
			rePosition[0] -= s*t;
			rePosition = pathOvershootHandler(rePosition, 15);
		}
		//return new position
		return rePosition;
	}
	
	/**
	 * pathOvershootHandler
	 * <p> handles path overshoot cases in the path function given the position of the bloon and the part of the map the check is being performed</p>
	 * @param p the position of the bloon
	 * @param c the part of the map the check is being performed
	 * @return the adjusted position of the bloon after overshoot is considered and handled
	 */
	private double[] pathOvershootHandler(double[] p, int c) {
		double[] rePosition = p;
		//DON'T ADD BREAKS in cases 1-14!!! Otherwise, multiple overshoots won't be handled properly.
		switch (c) {
		case 1:
			if (rePosition[0] > 6) {
				rePosition[1] += rePosition[0] - 6;
				rePosition[0] = 6;
			}
		case 2:
			if (rePosition[1] > 23) {
				rePosition[0] += rePosition[1] - 23;
				rePosition[1] = 23;
			}
		case 3:
			if (rePosition[0] > 33) {
				rePosition[1] += rePosition[0] - 33;
				rePosition[0] = 33;
			}
		case 4:
			if (rePosition[1] < 16) {
				rePosition[0] -= 16 - rePosition[1];
				rePosition[1] = 16;
			}
		case 5:
			if (rePosition[0] < 32) {
				rePosition[1] += 32 - rePosition[0];
				rePosition[0] = 32;
			}
		case 6:
			if (rePosition[1] > 22) {
				rePosition[0] += rePosition[1] - 22;
				rePosition[1] = 22;
			}
		case 7:
			if (rePosition[0] < 7) {
				rePosition[1] -= 7- rePosition[0];
				rePosition[0] = 7;
			}
		case 8:
			if (rePosition[1] < 5) {
				rePosition[0] += 5 - rePosition[1];
				rePosition[1] = 5;
			}
		case 9:
			if (rePosition[0] > 32) {
				rePosition[1] += rePosition[0] - 32;
				rePosition[0] = 32;
			}
		case 10:
			if (rePosition[1] > 11) {
				rePosition[0] += rePosition[1] - 11;
				rePosition[1] = 11;
			}
		case 11:
			if (rePosition[0] > 33) {
				rePosition[1] -= 33 - rePosition[0];
				rePosition[0] = 33;
			}
		case 12:
			if (rePosition[1] < 4) {
				rePosition[0] -= 4- rePosition[1];
				rePosition[1] = 4;
			}
		case 13:
			if (rePosition[0] < 6) {
				rePosition[1] += 6 - rePosition[0];
				rePosition[0] = 6;
			}
		case 14:
			if (rePosition[1] > 13) {
				rePosition[0] -= 13 - rePosition[1];
				rePosition[1] = 13;
			}
		case 15:
			if (rePosition[0] < 0) {
				//moves the bloon far away so that towers can't pop it anymore...
				rePosition[0] = Double.MAX_VALUE;
				rePosition[1] = Double.MAX_VALUE;
			}
			break;
		}
		return rePosition;
	}
	
	/**
	 * addBloon
	 * @param b the bloon to be add to the map
	 */
	public void addBloon(Bloon b) {
		bloons.add(b);
	}
	
	/**
	 * getPath
	 * <p></p>
	 * @return the path on the map defined by coordinates
	 */
	public LinkedList<Coordinate> getPath() {
		LinkedList<Coordinate> path = new LinkedList<Coordinate>();
		//adding the turns (in order)
		path.add(new Coordinate(0, 14));
		path.add(new Coordinate(6, 14));
		path.add(new Coordinate(6, 23));
		path.add(new Coordinate(33, 23));
		path.add(new Coordinate(33, 16));
		path.add(new Coordinate(32, 16));
		path.add(new Coordinate(32, 22));
		path.add(new Coordinate(7, 22));
		path.add(new Coordinate(7, 5));
		path.add(new Coordinate(32, 5));
		path.add(new Coordinate(32, 11));
		path.add(new Coordinate(33, 11));
		path.add(new Coordinate(33, 4));
		path.add(new Coordinate(6, 4));
		path.add(new Coordinate(6, 13));
		path.add(new Coordinate(0, 13));
		return path;
	}
}
