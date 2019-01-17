package msa6;

import java.util.LinkedList;

public abstract class Tower extends OnGameMap{
	//variable declarations
	private double attackSpeed;
	private String priority;
	private int range;
	private double nextAttack;
	
	/**
	 * constructor
	 * @param po the position of the tower
	 * @param as the attack speed of the tower (times per second)
	 * @param pr the priority setting of the tower
	 * @param r the radius of the tower's attack range
	 */
	public Tower(Coordinate po, double as, String pr, int r) {
		super(po);
		attackSpeed = as;
		priority = pr;
		range = r;
		nextAttack = 0;
	}
	
	/**
	 * getAttackSpeed
	 * @return the attack speed of the tower
	 */
	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	/**
	 * getProiority
	 * @return the priority setting of the tower
	 */
	public String getPriority() {
		return priority;
	}
	
	/**
	 * getRange
	 * @return the range of the tower
	 */
	public int getRange() {
		return range;
	}
	
	/**
	 * update
	 * @param bloons the bloons in range of the tower
	 * @return the projectile to be added to the map
	 */
	public abstract LinkedList<Projectile> update(LinkedList<Bloon> bloons, double t); 
	
	/**
	 * getPathIntersectionDistance
	 * @param paths the paths on the map
	 * @return distance from the start of each path to where the range of the tower intersects the path (used to calculate event times)
	 */
	public LinkedList<LinkedList<Double>> getPathIntersectionDistance(LinkedList<LinkedList<Coordinate>> paths) {
		LinkedList<LinkedList<Double>> distances = new LinkedList<LinkedList<Double>>();
		for (int i = 0; i < paths.size(); i++) {
			LinkedList<Coordinate> path = paths.get(i);
			distances.add(new LinkedList<Double>());
			for (int j = 1; j < path.size(); j++) {
				Coordinate p0 = path.get(j - 1);
				Coordinate p1 = path.get(j);
				boolean exists = false;
				//values for the quadratic equation
				double a;
				double b;
				double c;
				double d;
				//values for the two solutions
				double x1 = 0;
				double x2 = 0;
				double y1 = 0;
				double y2 = 0;
				//in the case the slope is infinity (a vertical line)
				if ((p1.getX() - p0.getX()) == 0) {
					//constants based off of circle equation
					x1 = p0.getX();
					x2 = p0.getX();
					//using quadratic formula
					a = 1;
					b = 1*getPosition().getY();
					c = Math.pow(getPosition().getY(), 2) + Math.pow(x1, 2) + Math.pow(getPosition().getX(), 2) - 2*getPosition().getX()*x1 - Math.pow(range, 2);
					d = Math.pow(b, 2) - 4*a*c;
					if (!(d < 0)) {
						y1 = (-b + Math.sqrt(d))/2/a;
						y2 = (-b - Math.sqrt(d))/2/a;
						exists = true;
					}
				}
				//when the slope is not infinity
				else {	
					//constants based off of circle equation
					double slope = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
					double ranCon = -slope*p0.getX() + p0.getY() - getPosition().getY(); //error slope == infinity?
					//using the quadratic formula
					a = Math.pow(slope, 2) + 1;
					b = -2*getPosition().getX() - 2*slope*ranCon;
					c = Math.pow(getPosition().getX(), 2) + Math.pow(ranCon, 2) - Math.pow(range, 2);
					d = Math.pow(b, 2) - 4*a*c;
					//solving for x and y using line and circle equations
					if (!(d < 0)){
						x1 = (-b + Math.sqrt(d))/2/a;
						x2 = (-b - Math.sqrt(d))/2/a;
						y1 = slope*x1 - slope*p0.getX() + p0.getY();
						y2 = slope*x2 - slope*p0.getX() + p0.getY();
						exists = true;
					}
				}
				if (exists) {
					if(!((x1 < p0.getX() && x1 < p1.getX()) || (x1 > p0.getX() && x1 > p1.getX()))) {
						if (!((y1 < p0.getY() && y1 < p1.getY()) || (y1 > p0.getY() && y1 > p1.getY()))) {
							double distance = 0;
							for(int k = 1; k < j; k++) {
								distance += getDistance(path.get(k - 1), path.get(k));
							}
							distances.get(i).add(distance + getDistance(p0, new Coordinate(x1, y1)));
						}
					}
					if(!((x2 < p0.getX() && x2 < p1.getX()) || (x2 > p0.getX() && x2 > p1.getX()))) {
						if (!((y2 < p0.getY() && y2 < p1.getY()) || (y2 > p0.getY() && y2 > p1.getY()))) {
							double distance = 0;
							for(int k = 1; k < j; k++) {
								distance += getDistance(path.get(k - 1), path.get(k));
							}
							distances.get(i).add(distance + getDistance(p0, new Coordinate(x2, y2)));
						}
					}
				}
			}
		}
		return distances;
	}
	
	public double getNextAttack() {
		return nextAttack;
	}
	
	public void setNextAttack(double n) {
		nextAttack = n;
	}
	
	public void update(double t) {
		nextAttack -= t;
		if (nextAttack < 0) {
			nextAttack = 0;
		}
	}
}
