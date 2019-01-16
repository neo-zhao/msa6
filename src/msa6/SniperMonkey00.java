package msa6;

import java.util.LinkedList;

public class SniperMonkey00 extends Tower{
	public SniperMonkey00(Coordinate position, String priority) {
		super(position, 0.45, priority, Integer.MAX_VALUE);
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
			double x = target.getPosition().getX();
			double y = target.getPosition().getY();
			path.add(new Coordinate(super.getPosition().getX() + x*1000, super.getPosition().getY() + y*1000));
			projectiles.add(new SniperBullet(super.position, path, 2, 1, 3));
		}
		return projectiles;
	}
}
