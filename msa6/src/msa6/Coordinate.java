package msa6;

/**
 * Coordinate
 * Used to define locations on the map in the simulation
 */
public class Coordinate {
	//variable declarations
	int x;
	int y;
	
	/**
	 * constructor for coordinate
	 * @param a the x value
	 * @param b the y value
	 */
	public Coordinate(int a, int b) {
		x = a;
		y = b;
	}
	
	/**
	 * getX
	 * @return the x value of the coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * getY
	 * @return the y value of the coordinate
	 */
	public int getY() {
		return y;
	}
}
