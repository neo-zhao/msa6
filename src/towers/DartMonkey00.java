package towers;

import java.util.ArrayList;
import java.util.LinkedList;

import framework.Bloon;
import framework.Coordinate;
import framework.MiscHelper;
import framework.Projectile;
import framework.Tower;
import projectiles.Dart;

public class DartMonkey00 extends Tower{
	//variable declarations
	
	//CONSTRUCTORS
	
	public DartMonkey00(Coordinate position, String priority) {
		super(position, priority, 100.0/700.0*MiscHelper.getMapLength(), 1.03);
	}
	
	//GETTERS AND SETTERS
	
	//OTHER METHODS
	public ArrayList<Projectile> update(LinkedList<Bloon> inRange, double elapsedTime) {
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		setNextAttack(getNextAttack() - elapsedTime);
		if (getNextAttack() <= 0) {
			setNextAttack(1/getAttackSpeed());
			Bloon target = inRange.getFirst();
			for (Bloon b: inRange) {
				if (getPriority() != MiscHelper.getPriorityClose()) {
					if (b.getRank(getPriority()) < target.getRank(getPriority())) {
						target = b;
					}
				}
				else {
					if (getPosition().distance(b.getPosition()) < getPosition().distance(target.getPosition())) {
						target = b;
					}
				}
			}
			ArrayList<Coordinate> path = new ArrayList<Coordinate>();
			path.add(extendPath(target));
			projectiles.add(new Dart(getPosition(), 1, path));
		}
		return projectiles;
	}
}