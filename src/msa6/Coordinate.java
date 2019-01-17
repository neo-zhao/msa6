package msa6;

/**
 * Coordinate
 * Used to define locations on the map in the simulation
 */
public class Coordinate {
	//variable declarations
	private double x;
	private double y;
	
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
	
	/**
	 * getDistance
	 * @param a the coordinate in question
	 * @return the distance between the two coordinates
	 */
	public double getDistance(Coordinate a, Coordinate b) {
		//just Distance formula
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY() , 2));
	}
	
	public void setX(double xValue) {
		x = xValue;
	}
	
	public void setY(double yValue) {
		y = yValue;
	}
}
