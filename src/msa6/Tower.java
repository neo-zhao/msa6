package msa6;

import java.util.LinkedList;

public abstract class Tower extends OnGameMap{
	//variable declarations
	double attackSpeed;
	String priority;
	int range;
	double nextAttack;
	
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
				double slope = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
				double ranCon = -slope*p0.getX() + p0.getY() - position.getY();
				//using the quadratic formula
				double a = Math.pow(slope, 2) + 1;
				double b = -2*position.getX() - 2*slope*ranCon;
				double c = Math.pow(position.getX(), 2) + Math.pow(ranCon, 2) - Math.pow(range, 2);
				double d = Math.pow(b, 2) - 4*a*c;
				if (!(d < 0)) {
					//solving for x and y using line and circle equations
					double x1 = (-b + Math.sqrt(d))/2/a;
					double x2 = (-b - Math.sqrt(d))/2/a;
					double y1 = slope*x1 - slope*p0.getX() + p0.getY();
					double y2 = slope*x2 - slope*p0.getX() + p0.getY();
					//checking if the intersection point is within the bounds of the line segment for both potential points
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
	
	public void update(double t) {
		nextAttack -= t;
		if (nextAttack < 0) {
			nextAttack = 0;
		}
	}
}
