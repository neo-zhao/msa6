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
		
	}
}
