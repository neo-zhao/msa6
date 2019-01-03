package msa6;

import java.util.Dictionary;
import java.util.LinkedList;

public class RedBloon extends Bloon {
	/**
	 * constructor
	 * @param position go figure...
	 * @param path go figure...
	 * @param rank go figure...
	 * @param spawnTime go figure...
	 */
	public RedBloon(Coordinate position, LinkedList<Coordinate> path, Dictionary<String, Integer> rank, double spawnTime) {
		super(position, path, 52, 1, 8, rank, spawnTime);
	}

	@Override
	/**
	 * pop
	 * <p>returns an empty list of bloons (red bloons don't release more bloons when popped)</p>
	 */
	public LinkedList<Bloon> pop(Projectile p) {
		return new LinkedList<Bloon>();
	}
}
