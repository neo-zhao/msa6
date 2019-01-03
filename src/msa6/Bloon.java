package msa6;

import java.util.Dictionary;
import java.util.LinkedList;

public abstract class Bloon {
	//variable declarations
	Coordinate position;
	LinkedList<Coordinate> path;
	double moveSpeed;
	int rbe;
	double radius;
	Dictionary<String, Integer> rank;
	double spawnTime;
	LinkedList<Effect> effects;

	/**
	 * constructor
	 * @param po the position of the bloon
	 * @param pa the path the bloon will take
	 * @param ms the move speed of the bloon
	 * @param rb the rbe value of the bloon
	 * @param rad the radius for the size of the bloon
	 * @param ran the dictionary of ranking for tower prioritization
	 */
	public Bloon(Coordinate po, LinkedList<Coordinate> pa, double ms, int rb, double rad, Dictionary<String, Integer> ran, double st) {
		position = po;
		path = pa;
		moveSpeed = ms;
		rbe = rb;
		radius = rad;
		rank = ran;
		spawnTime = st;
		effects = new LinkedList<Effect>();
	}

	/**
	 * pop
	 * @return bloons to be added to the map after this bloon is popped
	 */
	public abstract LinkedList<Bloon> pop(Projectile p); 
	
	/**
	 * getPosition
	 * @return the current position of the bloon
	 */
	public Coordinate getPosition() {
		return position;
	}

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
	 * getMoveSpeed
	 * @return the move speed of the bloon
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * getRbe
	 * @return the rbe value of the bloon
	 */
	public int getRbe() {
		return rbe;
	}

	/**
	 * getRadius
	 * @return the radius for the size of the bloon
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * getRank
	 * @param s the type of priority
	 * @return the rank for that priority
	 */
	public Integer getRank(String s) {
		return rank.get(s);
	}

	/**
	 * move
	 * <p>moves the bloon down its path given a certain amount of time</p>
	 * @param t the elapsed time in which the bloon is allowed to move
	 */
	public void update(double t) {
		double moveDistance = moveSpeed*t;
		while (moveDistance > 0) {
			//no overshoot, and not at the next coordinate on the path
			if (getDistance(path.getFirst()) > moveDistance) {
				double ratio = moveDistance / getDistance(path.getFirst());
				int x = (int) Math.floor(position.getX() + (path.getFirst().getX() - position.getX()) / ratio);
				int y = (int) Math.floor(position.getY() + (path.getFirst().getY() - position.getY()) / ratio);
				position = new Coordinate(x, y);
			}
			//if the distance overshoots the next coordinate on the path or is at the next coordinate on the path
			else {
				moveDistance = moveDistance - getDistance(path.getFirst());
				position = path.getFirst();
				path.removeFirst();
			}
		}
	}

	/**
	 * getSpawnTime
	 * @return the spawnTime of the bloon
	 */
	public double getSpawnTime() {
		return spawnTime;
	}
	
	/**
	 * getDistance
	 * @param a the coordinate in question
	 * @return the distance from the bloon to that coordinate
	 */
	public double getDistance(Coordinate a) {
		//just Pythagorean Theorem 
		return Math.sqrt(Math.pow(position.getX() - a.getX(), 2) + Math.pow(position.getY() - a.getY() , 2));
	}
	
	public void addEffect(Effect e) {
		effects.add(e);
	}
}
