package msa6;

import java.util.LinkedList;

public abstract class Tower {
	//variable declarations
	Coordinate position;
	double attackSpeed;
	String priority;
	int range;
	
	/**
	 * constructor
	 * @param po position
	 * @param pr priority
	 */
	public Tower(Coordinate po, double as, String pr, int r) {
		position = po;
		attackSpeed = as;
		priority = pr;
		range = r;
	}
	
	/**
	 * getPosition
	 * @return the position of the tower
	 */
	public Coordinate getPosition() {
		return position;
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
	 * attack
	 * @param bloons the bloons in range of the tower
	 * @return the projectile to be added to the map
	 */
	public abstract LinkedList<Projectile> attack(LinkedList<Bloon> bloons); 
}
