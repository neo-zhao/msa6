package framework;

public class OnGameMap {
	//variable declarations
	private Coordinate position;
	
	//CONSTRUSTORS
		
	public OnGameMap(Coordinate position) {
		this.position = position;
	}
	
	//GETTERS AND SETTERS
	
	/**
	 * getPosition
	 * @return the position of the game object
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * setPosition (point)
	 * @param position the new position of the game object (as a point)
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	/**
	 * setPosition (doubles)
	 * @param x the x value of the new position
	 * @param y the y value of the new position
	 */
	public void setPosition(double x, double y) {
		this.position = new Coordinate(x, y);
	}
	
	//OTHER METHODS
}
