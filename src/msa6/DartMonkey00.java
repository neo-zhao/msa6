package msa6;

import java.util.LinkedList;

public class DartMonkey00 extends Tower{
	/**
	 * constructor
	 * @param position the position of the tower on the map
	 * @param priority the priority setting of the tower; one of the following: "FIRST" "LAST" "CLOSE" "STRONG"
	 */
	public DartMonkey00(Coordinate position, String priority) {
		super(position, 1.03, priority, 100);
	}

	@Override
	public LinkedList<Projectile> update(LinkedList<Bloon> bloons, double t) {
		LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
		setNextAttack(getNextAttack() - t);
		if (getNextAttack() <=0) {
			Bloon target = bloons.getFirst();
			
			for (Bloon b: bloons) {
				if (b.getRank(getPriority()) < target.getRank(getPriority())) {
					target = b;
				}
			}
			LinkedList<Coordinate> path = new LinkedList<Coordinate>();
			double x = target.getPosition().getX() - getPosition().getX();
			double y = target.getPosition().getY() - getPosition().getY();
			path.add(new Coordinate(getPosition().getX() + x*1000, getPosition().getY() + y*1000));
			projectiles.add(new Dart(getPosition(), path, 1, 1, 5));
		}
		setNextAttack(getAttackSpeed()/1);
		return projectiles;
	}
}
