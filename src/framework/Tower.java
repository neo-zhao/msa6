package framework;

import java.awt.geom.Point2D;

public abstract class Tower extends OnGameMap{
	//variable declarations
	private String priority;
	
	//CONSTRUSTORS
	
	public Tower(Point2D.Double position, String priority) {
		super(position);
		this.priority = priority;
	}
	
	//GETTERS AND SETTERS
	
	//OTHER METHODS
}