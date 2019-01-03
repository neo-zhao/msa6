package msa6;

import java.util.LinkedList;

public class ProjectilePopOutput {
	//variable declarations
	LinkedList<Effect> effects;
	LinkedList<Projectile> projectiles;
	
	/**
	 * constructor
	 * @param e list of effects
	 * @param p list of projectiles
	 */
	public ProjectilePopOutput(LinkedList<Effect> e, LinkedList<Projectile> p) {
		effects = e;
		projectiles = p;
	}
	
	/**
	 * getEffects
	 * @return the list of effects
	 */
	public LinkedList<Effect> getEffects() {
		return effects;
	}
	
	/**
	 * getProjectiles
	 * @return the list of projectiles
	 */
	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
}
