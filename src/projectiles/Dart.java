package projectiles;

import java.util.ArrayList;

import framework.Bloon;
import framework.Coordinate;
import framework.MiscHelper;
import framework.Projectile;

public class Dart extends Projectile{
	//variable declarations
	
	//CONSTRUCTORS
	public Dart(Coordinate position, int durability, ArrayList<Coordinate> path) {
		super(position, 400.0/700.0*MiscHelper.getMapLength(), 5.0/700.0*MiscHelper.getMapLength(), 1, durability, path);
	}
	
	//GETTERS AND SETTERS
		
	//OTHER METHODS
	
	@Override
	public ArrayList<Projectile> pop(Bloon bloon) {
		if (bloon.getResistances().contains(MiscHelper.getResistanceLead())) {
			setDurability(0);
		}
		else {
			setDurability(getDurability() - 1);
		}
		return new ArrayList<Projectile>();
	}
}