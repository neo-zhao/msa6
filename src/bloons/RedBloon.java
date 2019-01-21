package bloons;

import java.util.ArrayList;
import java.util.HashSet;

import framework.Bloon;
import framework.BloonPopOutput;
import framework.MiscHelper;
import framework.Projectile;

public class RedBloon extends Bloon{
	//variable declarations
	
	//CONSTRUCTORS
	
	public RedBloon(double spawnTime, String ID) {
		super(spawnTime, 42.0/700.0*MiscHelper.getMapLength(), 10.0/700.0*MiscHelper.getMapLength(), new HashSet<String>(), 1, 0, "Red" + ID);
	}
	
	//GETTERS AND SETTERS
	
	//OTHER METHODS

	@Override
	public BloonPopOutput pop(Projectile projectile) {
		return new BloonPopOutput(new ArrayList<Bloon>(), new ArrayList<Projectile>());
	}
	
}