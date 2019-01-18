package framework;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Bloon extends Mobile{
	//variable declarations
	private double spawnTime;
	private int pathNumber;
	private HashSet<String> resistances;
	private int rbeValue;
	private HashMap<String, Integer> rank;
	private int health;
	
	//CONSTRUCTORS
	
	public Bloon(double spawnTime, double moveSpeed, double radius, HashSet<String> resistances, int rbeValue, int health) {
		super(new Coordinate(), moveSpeed, radius);
		this.spawnTime = spawnTime;
		this.pathNumber = 0;
		this.resistances = resistances;
		this.rbeValue = rbeValue;
		this.rank = new HashMap<String, Integer>();
		this.health = health;
	}
	
	//GETTERS AND SETTERS
	/**
	 * getSpawnTime
	 * @return the spawnTime of the Bloon
	 */
	public double getSpawnTime() {
		return spawnTime;
	}
	/**
	 * getPathNumber
	 * @return the path number of the bloon
	 */
	public int getPathNumber() {
		return pathNumber;
	}
	/**
	 * setPathNumber
	 * @param pathNumber the new pathNumber of the bloon
	 */
	public void setPathNumber(int pathNumber) {
		this.pathNumber = pathNumber;
	}
	/**
	 * getResistances
	 * @return the resistances of this bloon
	 */
	public HashSet<String> getResistances() {
		return resistances;
	}
	/**
	 * getRbeValue
	 * @return the rbeValue of the bloon
	 */
	public int getRbeValue() {
		return rbeValue;
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
	//OTHER METHODS
	
	/**
	 * pop
	 * @param projectile the projectile attempting to pop the bloon
	 * @return the bloon pop output given the bloon and projectile
	 */
	public abstract BloonPopOutput pop(Projectile projectile); 
}
