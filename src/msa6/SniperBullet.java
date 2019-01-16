package msa6;

import java.util.LinkedList;

public class SniperBullet extends Projectile{
	
	/**
	 * constructor
	 * @param po the position of the projectile
	 * @param pa the path of the projectile
	 * @param rb the rbe pop of the projectile
	 * @param d the durability of the projectile
	 * @param ra the radius (size) of the projectile
	 */

	public SniperBullet(Coordinate po, LinkedList<Coordinate> pa, int rb, int d, double ra) {
		super(po, pa, 999999, rb, d, ra);
	}

	@Override
	public ProjectilePopOutput pop() {
		super.durability --;
		return new ProjectilePopOutput(new LinkedList<Effect>(), new LinkedList<Projectile>());
	}
}
