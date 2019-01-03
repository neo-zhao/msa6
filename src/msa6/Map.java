package msa6;

import java.util.LinkedList;

public abstract class Map {
	//variable declarations
	int livesLost;
	LinkedList<Bloon> bloons;
	LinkedList<Projectile> projectiles;
	LinkedList<Tower> towers;
	LinkedList<LinkedList<Coordinate>> paths;
	
	/**
	 * constructor
	 * @param p the paths on the map
	 */
	public Map(LinkedList<LinkedList<Coordinate>> p) {
		livesLost = 0;
		bloons = new LinkedList<Bloon>();
		projectiles = new LinkedList<Projectile>();
		towers = new LinkedList<Tower>();
		paths = p;
	}
	
	/**
	 * getPaths
	 * @return the paths on the map defined by coordinates
	 */
	public LinkedList<LinkedList<Coordinate>> getPaths() {
		return paths;
	}
	
	/**
	 * addTower
	 * @param t the tower to be added to the map
	 */
	public void addTower(Tower t) {
		towers.add(t);
	}
	
	/**
	 * addBloon
	 * @param b the bloon to be add to the map
	 */
	public void addBloon(Bloon b) {
		bloons.add(b);
	}
	
}
