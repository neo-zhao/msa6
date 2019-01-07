package msa6;

import java.util.LinkedList;

public class Dart extends Projectile{

	public Dart(Coordinate po, LinkedList<Coordinate> pa, int rb, int d, double ra) {
		super(po, pa, 400, rb, d, ra);
	}

	@Override
	public ProjectilePopOutput pop() {
		super.durability --;
		return new ProjectilePopOutput(new LinkedList<Effect>(), new LinkedList<Projectile>());
	}
}
