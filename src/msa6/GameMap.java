package msa6;

import java.util.LinkedList;

public abstract class GameMap {
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
	public GameMap(LinkedList<LinkedList<Coordinate>> p) {
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
	
	/**
	 * getBloons
	 * @return a list of all the bloons on the map
	 */
	public LinkedList<Bloon> getBloons() {
		return bloons;
	} 
	
	/**
	 * getTowers
	 * @return a list of all the towers on the map
	 */
	public LinkedList<Tower> getTowers() {
		return towers;
	}
	
	/**
	 * getProjectiles
	 * @return the projectiles on the map
	 */
	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	/**
	 * addProjectile
	 * @param p the projectile to be added
	 */
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
}
