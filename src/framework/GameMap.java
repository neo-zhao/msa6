package framework;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameMap {
	//variable declarations
	private ArrayList<ArrayList<Coordinate>> paths;
	private ArrayList<Tower> towers;
	private LinkedList<Bloon> bloons;
	private LinkedList<Projectile> projectiles;
	
	//CONSTRUCTORS
	/**
	 * default constructor
	 */
	public GameMap() {
		paths = new ArrayList<ArrayList<Coordinate>>();
		towers = new ArrayList<Tower>();
		bloons = new LinkedList<Bloon>();
		projectiles = new LinkedList<Projectile>();
	}
	
	//GETTERS AND SETTERS
	/**
	 * getPaths
	 * @return the paths on the map
	 */
	public ArrayList<ArrayList<Coordinate>> getPaths() {
		return paths;
	}
	/**
	 * getTowers
	 * @return the towers on the map
	 */
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	/**
	 * getBloons
	 * @return the list of bloons on the map
	 */
	public LinkedList<Bloon> getBloons() {
		return bloons;
	}
	/**
	 * getProjectiles
	 * @return the projectiles on the map
	 */
	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	//OTHER METHODS
	/**
	 * addPath
	 * @param path the path (an arrayList of points) to be added to the list of paths on the map
	 */
	public void addPath(ArrayList<Coordinate> path) {
		paths.add(path);
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
	 * @param b the bloon to be added to the map
	 */
	public void addBloon(Bloon b) {
		bloons.add(b);
	}
	/**
	 * addProjectile
	 * @param p the projectile to be added to the map
	 */
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	/**
	 * outOfBounds
	 * @param position the position of the object being tests
	 * @return true if the object is outside the bounds of the map and false if it is not
	 */
	public boolean outOfBounds(Coordinate position) {
		if(position.getX() < 0 || position.getX() > MiscHelper.getMapLength()) {
			return true;
		}
		if(position.getY() < 0 || position.getY() > MiscHelper.getMapHeight()) {
			return true;
		}
		return false;
	}
}