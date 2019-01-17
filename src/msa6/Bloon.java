package msa6;

import java.util.LinkedList;
import java.util.HashMap;

public abstract class Bloon extends Mobile {
	// variable declarations
	private int rbe;
	private HashMap<String, Integer> rank;
	private double spawnTime;
	private LinkedList<Effect> effects;
	private int pathNumber;

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
		//uses constructor for Mobile
		super(po, pa, ms, rad);
		
		//initialize other variables
		rbe = rb;
		rank = ran;
		spawnTime = st;
		effects = new LinkedList<Effect>();
		pathNumber = pn;
	}

	/**
	 * constructor 2 (the easier one)
	 * 
	 * @param pa  the path the bloon must take
	 * @param ms  the move speed of the bloon
	 * @param rad the radius (size) of the bloon
	 * @param rb  the rbe value of the bloon
	 * @param st  the spawn time for the bloon
	 */
	public Bloon(LinkedList<Coordinate> pa, double ms, double rad, int rb, double st) {
		//uses constructor for Mobile
		super(null, pa, ms, rad);
		
		//initialize other variables
		rbe = rb;
		rank = new HashMap<String, Integer>();
		spawnTime = st;
		pathNumber = 0;
	}

	//Abstract Methods
	
	/**
	 * pop
	 * 
	 * @return bloons to be added to the map after this bloon is popped
	 */
	public abstract LinkedList<Bloon> pop(Projectile p);
	
	//Getters and Setters
	
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
	 * setRank
	 * 
	 * @param s the rank category
	 * @param i the rank number
	 */
	public void setRank(String s, int i) {
		rank.put(s, i);
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
	 * setSpawnTime
	 * 
	 * @param t the spawn time of the bloon
	 */
	public void setSpawnTime(double t) {
		spawnTime = t;
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
	 * setPathNumber
	 * 
	 * @param i the new path number for the bloon
	 */
	public void setPathnumber(int i) {
		pathNumber = i;
	}
	
	//Other Methods

	/**
	 * addEffect
	 * 
	 * @param e
	 */
	public void addEffect(Effect e) {
		effects.add(e);
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
			times.add(d / super.getMoveSpeed());
		}
		return times;
	}

	public boolean inRange(Tower t) {
		if (getPosition().getDistance(getPosition(), t.getPosition()) <= (t.getRange() + getRadius())) {
			return true;
		}
		return false;
	}
}