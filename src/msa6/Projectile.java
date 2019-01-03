package msa6;

import java.util.LinkedList;

public abstract class Projectile {
	//variable declarations
	Coordinate position;
	LinkedList<Coordinate> path;
	double moveSpeed;
	int rbePop;
	int durability;
	double radius;
	
	public Projectile(Coordinate po, LinkedList<Coordinate> pa, double ms, int rb, int d, double ra) {
		position = po;
		path = pa;
		moveSpeed = ms;
		rbePop = rb;
		durability = d;
		radius = ra;
	}
	
	/**
	 * pop
	 * <p>returns the list of effects to apply to target bloons and updates parameters of the projectile</p>
	 * @return the list of effects and projectiles that result from contact with this projectile
	 */
	public abstract ProjectilePopOutput pop();
	
	/**
	 * getPosition
	 * @return the current position of the projectile
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * getPath
	 * @return the path of the projectile
	 */
	public LinkedList<Coordinate> getPath() {
		return path;
	}
	
	/**
	 * getMoveSpeed()
	 * @return the move speed of the projectile
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	/**
	 * getRbePop
	 * @return the number of layers this projectile pops on contact
	 */
	public int getRbePop() {
		return rbePop;
	}
	
	/**
	 * getDurability
	 * @return the number of bloons this projectile can pop
	 */
	public int getDurability() {
		return durability;
	}
	
	/**
	 * getRadius
	 * @return the radius of the projectile
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * move
	 * <p>moves the projectile down its path given a certain amount of time</p>
	 * @param t the elapsed time in which the projectile is allowed to move
	 */
	public void move(double t) {
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
				if (path.isEmpty())
				{
					durability = 0;
				}
			}
		}
	}
	
	/**
	 * getDistance
	 * @param a the coordinate in question
	 * @return the distance from the projectile to that coordinate
	 */
	public double getDistance(Coordinate a) {
		//just Pythagorean Theorem 
		return Math.sqrt(Math.pow(position.getX() - a.getX(), 2) + Math.pow(position.getY() - a.getY() , 2));
	}
}
