package msa6;

public class Coordinate {
	//variable declarations
	double x;
	double y;
	
	/**
	 * constructor for coordinate
	 * @param a the x value
	 * @param b the y value
	 */
	public Coordinate(double a, double b) {
		x = a;
		y = b;
	}
	
	/**
	 * getX
	 * @return the x value of the coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * getY
	 * @return the y value of the coordinate
	 */
	public double getY() {
		return y;
	}
}
