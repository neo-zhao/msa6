package msa6;

import java.util.LinkedList;

public class NinjaMonkey00 extends Tower{
	public NinjaMonkey00(Coordinate position, String priority) {
		super(position, 1.67, priority, 100); print(f) //TODO I inserted this print statement to get a red
															//x for attention; this thing needs an updated
															//range value.
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
			projectiles.add(new Shuriken(super.position, path, 1, 2, 5));
		}
		return projectiles;
	}
}
