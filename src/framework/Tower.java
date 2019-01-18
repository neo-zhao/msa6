package framework;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Tower extends OnGameMap{
	//variable declarations
	private String priority;
	private double range;
	private double attackSpeed; //attacks per second
	private double nextAttack;
	
	//CONSTRUSTORS
	
	public Tower(Coordinate position, String priority, double range, double attackSpeed) {
		super(position);
		this.priority = priority;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.nextAttack = 0;
	}
	
	//GETTERS AND SETTERS
	/**
	 * getPriority
	 * @return the priority setting of the tower
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * getNextAttack
	 * @return the time until nextAttack
	 */
	public double getNextAttack() {
		return nextAttack;
	}
	/**
	 * setNextAttack
	 * @param nextAttack updates nextAttack time
	 */
	public void setNextAttack(double nextAttack) {
		this.nextAttack = nextAttack;
	}
	/**
	 * getAttackSpeed
	 * @return
	 */
	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	//OTHER METHODS
	/**
	 * inRange
	 * @param b the bloon that is being tested
	 * @return true if the bloon is in range, false if the bloon is not in range
	 */
	public boolean inRange(Bloon b) {
		return getPosition().distance(b.getPosition()) <= (range + b.getRadius());
	}
	
	public void update(double elapsedTime) {
		nextAttack -= elapsedTime;
	}
	
	public abstract ArrayList<Projectile> update(LinkedList<Bloon> inRange, double elapsedTime); 
	
	public Coordinate extendPath(Bloon target) {
		return new Coordinate((target.getPosition().getX() - getPosition().getX())*1000, (target.getPosition().getY() - getPosition().getY()));
	}
}