package framework;

import java.util.ArrayList;

public class Mobile extends OnGameMap{
	//variable declarations
	private double moveSpeed;
	private ArrayList<Coordinate> path;
	private int pathIndex;
	private double distanceTraveled;
	private double radius;
	
	//CONSTRUCTORS	
	
	public Mobile(Coordinate position, double moveSpeed, double radius) {
		super(position);
		this.moveSpeed = moveSpeed;
		this.path = null;
		this.pathIndex = 0;
		this.distanceTraveled = 0;
		this.radius = radius;
	}
	
	public Mobile(Coordinate position, double moveSpeed, double radius, ArrayList<Coordinate> path) {
		this(position, moveSpeed, radius);
		this.path = path;
	}
		
	//GETTERS AND SETTERS
	/**
	 * getPath
	 * @return the path of the mobile object
	 */
	public ArrayList<Coordinate> getPath() {
		return path;
	}
	/**
	 * setPath
	 * @param arrayList the new path of the mobile object
	 */
	public void setPath(ArrayList<Coordinate> arrayList) {
		this.path = arrayList;
	}
	/**
	 * getDistanceTraveled
	 * @return
	 */
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
	/**
	 * getRadius
	 * @return the radius of the mobile object
	 */
	public double getRadius() {
		return radius;
	}
	
	//OTHER METHODS
	
	/**
	 * move
	 * @param elapsedTime the amount of time the mobile object has to move
	 */
	public void move(double elapsedTime) {
		double moveDistance = moveSpeed*elapsedTime;
		distanceTraveled += moveDistance;
		while (moveDistance > 0) {
			double distanceToNextTurn = getPosition().distance(path.get(pathIndex));
			//no overshoot, and not at the next coordinate on the path
			if (distanceToNextTurn > moveDistance) {
				double ratio = moveDistance / distanceToNextTurn;
				double x = getPosition().getX() + (path.get(pathIndex).getX() - getPosition().getX()) * ratio;
				double y = getPosition().getY() + (path.get(pathIndex).getY() - getPosition().getY()) * ratio;
				setPosition(x, y);
			}
			//if the distance overshoots the next coordinate on the path or is at the next coordinate on the path
			else {
				setPosition(path.get(pathIndex));
				pathIndex ++;
			}
			moveDistance -= getPosition().distance(path.get(pathIndex));
		}
	}
}
