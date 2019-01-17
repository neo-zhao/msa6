package framework;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameMap {
	//variable declarations
	private ArrayList<ArrayList<Point2D.Double>> paths;
	private ArrayList<Tower> towers;
	private LinkedList<Bloon> bloons;
	
	//CONSTRUCTORS
	
	/**
	 * default constructor
	 */
	public GameMap() {
		paths = new ArrayList<ArrayList<Point2D.Double>>();
		towers = new ArrayList<Tower>();
	}
	
	//GETTERS AND SETTERS
	
	//OTHER METHODS
	
	/**
	 * addPath
	 * @param path the path (an arrayList of points) to be added to the list of paths on the map
	 */
	public void addPath(ArrayList<Point2D.Double> path) {
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

}