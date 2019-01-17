package msa6;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class GameMap {
	//variable declarations
	private ArrayList<ArrayList<Point2D.Double>> paths;
	
	//CONSTRUCTORS
	
	/**
	 * default constructor
	 */
	public GameMap() {
		paths = new ArrayList<ArrayList<Point2D.Double>>();
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

}