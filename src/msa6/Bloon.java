package msa6;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Bloon extends Mobile {
	// variable declarations
	int rbe;
	HashMap<String, Integer> rank;
	double spawnTime;
	LinkedList<Effect> effects;
	int pathNumber;
	double distanceTraveled;

	/**
	 * constructor default
	 * 
	 * @param po  the position of the bloon
	 * @param pa  the path the bloon will take
	 * @param ms  the movement speed of the bloon
	 * @param rb  the rbe value of the bloon
	 * @param rad the radius of the bloon
	 * @param ran the priority ranking for the bloon
	 * @param st  the spawn time of the bloon
	 * @param pn  the path number of the bloon
	 * @param d   the distance the bloon has traveled
	 */
	public Bloon(Coordinate po, LinkedList<Coordinate> pa, double ms, int rb, double rad, HashMap<String, Integer> ran,
			double st, int pn, double d) {
		super(po, pa, ms, rad);
		rbe = rb;
		rank = ran;
		spawnTime = st;
		effects = new LinkedList<Effect>();
		pathNumber = pn;
		distanceTraveled = d;
	}

	/**
	 * constructor 2
	 * 
	 * @param pa  the path the bloon must take
	 * @param ms  the move speed of the bloon
	 * @param rad the radius (size) of the bloon
	 * @param rb  the rbe value of the bloon
	 * @param st  the spawn time for the bloon
	 */
	public Bloon(LinkedList<Coordinate> pa, double ms, double rad, int rb, double st) {
		super(null, pa, ms, rad);
		rbe = rb;
		rank = new HashMap<String, Integer>();
		spawnTime = st;
		pathNumber = 0;
		distanceTraveled = 0;
	}

	/**
	 * pop
	 * 
	 * @return bloons to be added to the map after this bloon is popped
	 */
	public abstract LinkedList<Bloon> pop(Projectile p);

	/**
	 * getRbe
	 * 
	 * @return the rbe value of the bloon
	 */
	public int getRbe() {
		return rbe;
	}

	/**
	 * getRank
	 * 
	 * @param s the type of priority
	 * @return the rank for that priority
	 */
	public Integer getRank(String s) {
		return rank.get(s);
	}

	/**
	 * getSpawnTime
	 * 
	 * @return the spawnTime of the bloon
	 */
	public double getSpawnTime() {
		return spawnTime;
	}

	/**
	 * addEffect
	 * 
	 * @param e
	 */
	public void addEffect(Effect e) {
		effects.add(e);
	}

	/**
	 * setPathNumber
	 * 
	 * @param i the new path number for the bloon
	 */
	public void setPathnumber(int i) {
		pathNumber = i;
	}

	/**
	 * getPathNumber
	 * 
	 * @return the path number of the bloon
	 */
	public int getPathNumber() {
		return pathNumber;
	}

	/**
	 * getEncounterTimes
	 * 
	 * @param distances the distances the bloon has to travel (from the start of the
	 *                  map) to get in range of a tower
	 * @return the time it takes to reach a tower
	 */
	public LinkedList<Double> getEncounterTimes(LinkedList<LinkedList<Double>> distances) {
		LinkedList<Double> times = new LinkedList<Double>();
		for (double d : distances.get(getPathNumber())) {
			times.add(d / super.moveSpeed);
		}
		return times;
	}

	/**
	 * getDistanceTraveled
	 * 
	 * @return the distance traveled by the bloon
	 */
	public double getDistanceTraveled() {
		return distanceTraveled;
	}

	/**
	 * setRank
	 * 
	 * @param s the rank category
	 * @param i the rank number
	 */
	public void setRank(String s, int i) {
		rank.put(s, i);
	}

	@Override
	public void update(double t) {
		double moveDistance = moveSpeed * t;
		distanceTraveled += moveDistance;
		while (moveDistance > 0) {
			if (getDistance(path.getFirst(), position) > moveDistance) {
				// no overshoot, and not at the next coordinate on the path
				moveDistance -= getDistance(path.getFirst(), position);
				double ratio = moveDistance / getDistance(path.getFirst(), position);
				double x = Math.floor(position.getX() + (path.getFirst().getX() - position.getX()) / ratio);
				double y = Math.floor(position.getY() + (path.getFirst().getY() - position.getY()) / ratio);
				position = new Coordinate(x, y);
			} else {
				// if the distance overshoots the next coordinate on the path or is at the next
				// coordinate on the path
				moveDistance -= getDistance(path.getFirst(), position);
				position = path.getFirst();
				path.removeFirst();
			}
		}
	}

	public void setSpawnTime(double t) {
		spawnTime = t;
	}

	public boolean inRange(Tower t) {
		if (position.getDistance(position, t.getPosition()) <= (t.getRange() + radius)) {
			return true;
		}
		return false;
	}
}
