package framework;

import java.util.ArrayList;

public class BloonPopOutput {
	//variable declarations
	ArrayList<Bloon> bloons;
	ArrayList<Projectile> projectiles;
	
	//CONSTRUCTORS
	/**
	 * constructor
	 * @param bloons the bloons of the bloon pop output
	 * @param projectiles the projectiles of the bloon pop output
	 */
	public BloonPopOutput(ArrayList<Bloon> bloons, ArrayList<Projectile> projectiles) {
		this.bloons = bloons;
		this.projectiles = projectiles;
	}
	
	//GETTERS AND SETTERS
	/**
	 * getBloons
	 * @return the bloons of the bloon pop output
	 */
	public ArrayList<Bloon> getBloons() {
		return bloons;
	}
	/**
	 * getProjectiles
	 * @return the projectiles of the bloon pop output
	 */
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	//OTHER METHODS
}
