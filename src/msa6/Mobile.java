package msa6;

import java.util.LinkedList;

public abstract class Mobile extends OnGameMap{
	//variable declarations
	LinkedList<Coordinate> path;
	double moveSpeed;
	double radius;
	
	public Mobile(Coordinate po, LinkedList<Coordinate> pa, double ms, double r) {
		super(po);
		path = pa;
		moveSpeed = ms;
		radius = r;
	}
	
	/**
	 * update
	 * <p>updates the mobile object given a certain amount of time; moves it</p>
	 * @param t the elapsed time in which the mobile object is allowed to move
	 */
	public void update(double t) {
		double moveDistance = moveSpeed*t;
		while (moveDistance > 0) {
			//no overshoot, and not at the next coordinate on the path
			if (getDistance(path.getFirst(), position) > moveDistance) {
				double ratio = moveDistance / getDistance(path.getFirst(), position);
				double x = Math.floor(position.getX() + (path.getFirst().getX() - position.getX()) / ratio);
				double y = Math.floor(position.getY() + (path.getFirst().getY() - position.getY()) / ratio);
				position = new Coordinate(x, y);
			}
			//if the distance overshoots the next coordinate on the path or is at the next coordinate on the path
			else {
			moveDistance = moveDistance - getDistance(path.getFirst(), position);
			position = path.getFirst();
			path.removeFirst();
			}
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
		path = new LinkedList<Coordinate>();
		for(Coordinate c: p) {
			path.add(c);
		}
	}
	
	/**
	 * getRadius
	 * @return the radius for the size of the bloon
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * getMove Speed
	 * @return the move speed of the mobile object
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	/**
	 * setPosition
	 * @param c the new position of the mobile object
	 */
	public void setPosition(Coordinate c) {
		position = c;
	}
}
