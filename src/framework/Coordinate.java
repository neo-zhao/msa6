package framework;

public class Coordinate {
	//variable declarations
	double x;
	double y;
	
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate() {
		x = 0;
		y = 0;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Coordinate coordinate) {
		return Math.sqrt(Math.pow(coordinate.getX() - x, 2) + Math.pow(coordinate.getY() - y, 2));
	}
}
