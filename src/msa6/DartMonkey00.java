package msa6;

import java.util.LinkedList;

public class DartMonkey00 extends Tower{
	public DartMonkey00(Coordinate position, String priority) {
		super(position, 1.03, priority, 100);
	}

	@Override
	public LinkedList<Projectile> update(LinkedList<Bloon> bloons, double t) {
		LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
		nextAttack -= t;
		if (nextAttack <=0) {
			Bloon target = bloons.getFirst();
			for (Bloon b: bloons) {
				if (b.getRank(super.priority) < target.getRank(super.priority)) {
					target = b;
				}
			}
			LinkedList<Coordinate> path = new LinkedList<Coordinate>();
			double x = target.getPosition().getX() - super.position.getX();
			double y = target.getPosition().getY() - super.position.getY();
			path.add(new Coordinate(super.getPosition().getX() + x*1000, super.getPosition().getY() + y*1000));
			projectiles.add(new Dart(super.position, path, 1, 1, 5));
		}
		return projectiles;
	}
}
