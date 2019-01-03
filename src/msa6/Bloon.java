package msa6;

import java.util.LinkedList;

public class Bloon {
	//variable declarations
	int rbe;
	double speed;
	Coordinate position;
	LinkedList<Coordinate> path;
	
	/**
	 * constructor
	 * @param r the red bloon equivalent of the bloon
	 * @param s the speed of the bloon
	 * @param po the current position of the bloon
	 * @param pa the list of coordinates the bloon must pass through (in order)
	 */
	public Bloon(int r, double s, Coordinate po, LinkedList<Coordinate> pa) {
		rbe = r;
		speed = s;
		position = po;
		path = pa;
	}
	
	
}
