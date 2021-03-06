package msa6;

public abstract class OnGameMap {
	//variable declarations
	private Coordinate position;
	
	/**
	 * constructor
	 * @param p the position of the object
	 */
	public OnGameMap(Coordinate p) {
		position = p;
	}
	
	/**
	 * getPosition
	 * @return the current position of the object
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * setPostition
	 * @param c change the position of the game object
	 */
	public void setPosition(Coordinate c) {
		position = c;
	}
	
	/**
	 * getDistance
	 * @param a the coordinate in question
	 * @return the distance between the two coordinates
	 */
	public static double getDistance(Coordinate a, Coordinate b) {
		//just Distance formula
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY() , 2));
	}
}
