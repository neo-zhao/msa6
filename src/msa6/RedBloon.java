package msa6;

import java.util.HashMap;
import java.util.LinkedList;

public class RedBloon extends Bloon {
	/**
	 * constructor
	 * @param position go figure...
	 * @param path go figure...
	 * @param rank go figure...
	 * @param spawnTime go figure...
	 */
	public RedBloon(Coordinate position, LinkedList<Coordinate> path, HashMap<String, Integer> rank, double spawnTime, int pathNumber, double distanceTraveled) {
		super(position, path, 52, 1, 8, rank, spawnTime, pathNumber, distanceTraveled);
	}
	
	/**
	 * constructor 2
	 * @param position
	 * @param path
	 * @param spawnTime
	 */
	public RedBloon(double spawnTime) {
		super(null, 52, 8, 1, spawnTime);
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
