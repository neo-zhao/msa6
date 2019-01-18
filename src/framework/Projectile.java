package framework;

import java.util.ArrayList;
import java.util.HashSet;

public abstract  class Projectile extends Mobile {
	//variable declarations
	private int instantPopCount;
	private int durability;
	private HashSet<Effect> effects;
	
	//CONSTRUCTORS
	public Projectile(Coordinate position, double moveSpeed, double radius, int instantPopCount, int durability) {
		super(position, moveSpeed, radius);
		this.instantPopCount = instantPopCount;
		this.durability = durability;
	}
	public Projectile(Coordinate position, double moveSpeed, double radius, int instantPopCount, int durability, ArrayList<Coordinate> path) {
		super(position, moveSpeed, radius, path);
		this.instantPopCount = instantPopCount;
		this.durability = durability;
	}
	
	//GETTERS AND SETTERS
	/**
	 * getInstantPopCount
	 * @return the instant pop count of this projectile
	 */
	public int getInstantPopCount() {
		return instantPopCount;
	}
	/**
	 * getDurability
	 * @return the durability of the projectile
	 */
	public int getDurability() {
		return durability;
	}
	/**
	 * setDurability
	 * @param durability the new durability of the projectile
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}
	/**
	 * getEffects
	 * @return the effects the projectile causes
	 */
	public HashSet<Effect> getEffects(){
		return effects;
	}
	
	//OTHER METHODS
	/**
	 * pop
	 * @param bloon the bloon the projectile is trying to pop
	 * @return the projectiles that spawn from impact
	 */
	public abstract ArrayList<Projectile> pop(Bloon bloon); 
	
}
