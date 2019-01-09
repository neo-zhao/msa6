package msa6;

import java.util.LinkedList;

public abstract class Projectile extends Mobile{
	//variable declarations
	int rbePop;
	int durability;
	
	/**
	 * constructor
	 * @param po the position of the projectile
	 * @param pa the path of the projectile
	 * @param ms the movespeed of the projectile
	 * @param rb the rbe pop of the projectile
	 * @param d the durability of the projectile
	 * @param ra the radius (size) of the projectile
	 */
	public Projectile(Coordinate po, LinkedList<Coordinate> pa, double ms, int rb, int d, double ra) {
		super(po, pa, ms, ra);
		rbePop = rb;
		durability = d;
	}
	
	/**
	 * pop
	 * <p>returns the list of effects to apply to target bloons and updates parameters of the projectile</p>
	 * @return the list of effects and projectiles that result from contact with this projectile
	 */
	public abstract ProjectilePopOutput pop();
	
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
	
	public LinkedList<Double> getIntersectionTimes(LinkedList<LinkedList<Coordinate>> paths) {
		LinkedList<Double> times = new LinkedList<Double>();
		for (int i = 0; i < paths.size(); i++) {
			LinkedList<Coordinate> path = paths.get(i);
			for (int j = 1; j < path.size(); j++) {
				//making the line equation of the map path segment
				Coordinate mp0 = path.get(j - 1);
				Coordinate mp1 = path.get(j);
				double mSlope = (mp1.getY() - mp0.getY())/(mp1.getX() - mp0.getX());
				//making the line equation of the projectile
				for (int k = 1; k < super.path.size(); k++) {
					Coordinate pp0 = path.get(k - 1);
					Coordinate pp1 = path.get(k);
					double pSlope = (pp1.getY() - pp0.getY())/(pp1.getX() - pp0.getX());
					double x = (mSlope*mp0.getX() - pSlope*pp0.getX() + pp0.getY() - mp0.getY())/(mSlope - pSlope);
					double y = mSlope*(x - mp0.getX()) + mp0.getY();
					//checking if the intersection point is within the bounds of the line segment for both potential points
					if(!((x < mp0.getX() && x < mp1.getX()) || (x > mp0.getX() && x > mp1.getX()))) {
						if (!((y < mp0.getY() && y < mp1.getY()) || (y > mp0.getY() && y > mp1.getY()))) {
							if(!((x < pp0.getX() && x < pp1.getX()) || (x > pp0.getX() && x > pp1.getX()))) {
								if (!((y < mp0.getY() && y < pp1.getY()) || (y > mp0.getY() && y > pp1.getY()))) {
									double distance = getDistance(position, new Coordinate(x,y));
									times.add(distance / moveSpeed);
								}
							}
						}
					}
				}
			}	
		}
		return times;
	}
}
