package msa6;

import java.util.LinkedList;

public abstract class Mobile extends OnGameMap{
	//variable declarations
	private LinkedList<Coordinate> path;
	private double moveSpeed;
	private double radius;
	private int moveIndex;
	private double distanceTraveled;
	
	/**
	 * constructor default
	 * @param po the position of the mobile object
	 * @param pa the path the mobile object will take
	 * @param ms the move speed of the object
	 * @param r the radius (size) of the object
	 */
	public Mobile(Coordinate po, LinkedList<Coordinate> pa, double ms, double r) {
		//uses constructor of OnGameMap
		super(po);
		//other variables
		path = pa;
		moveSpeed = ms;
		radius = r;
		moveIndex =0;
		distanceTraveled = 0;
	}
	
	/**
	 * update
	 * <p>updates the mobile object given a certain amount of time; moves it</p>
	 * @param t the elapsed time in which the mobile object is allowed to move
	 */
	public void update(double t) {
		double moveDistance = moveSpeed*t;
		distanceTraveled += moveDistance;
		while (moveDistance > 0) {
			//no overshoot, and not at the next coordinate on the path
			if (getDistance(path.get(moveIndex), getPosition()) > moveDistance) {
				double ratio = moveDistance / getDistance(path.get(moveIndex), getPosition());
				double x = Math.floor(getPosition().getX() + (path.get(moveIndex).getX() - getPosition().getX()) / ratio);
				double y = Math.floor(getPosition().getY() + (path.get(moveIndex).getY() - getPosition().getY()) / ratio);
				setPosition(new Coordinate(x, y));
			}
			//if the distance overshoots the next coordinate on the path or is at the next coordinate on the path
			else {
				setPosition(path.get(moveIndex));
				moveIndex ++;
			}
			moveDistance -= getDistance(path.getFirst(), getPosition());
		}
	}
		
	//Getters and Setters
	
	/**
	 * getPath
	 * @return the path that the bloon takes
	 */
	public LinkedList<Coordinate> getPath() {
		return path;
	}
	
	/**
	 * setPath
	 * @param p the new path the bloon will take
	 */
	public void setPath(LinkedList<Coordinate> p) {
		path = p;
	}
	
	/**
	 * getRadius
	 * @return the radius for the size of the bloon
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * getMoveSpeed
	 * @return the move speed of the mobile object
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	/**
	 * getDistanceTraveled
	 * 
	 * @return the distance traveled by the bloon
	 */
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
}
