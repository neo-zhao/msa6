package map;

import java.util.ArrayList;

import framework.Coordinate;
import framework.GameMap;
import framework.MiscHelper;

public class BrickWallMap extends GameMap {
	
	/**
	 * default constructor
	 */
	public BrickWallMap() {
		//applies super constructor
		super();
		
		//adds path
		ArrayList<Coordinate> path1 = new ArrayList<Coordinate>();
		path1.add(new Coordinate(0.0, 264.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(96.0/700.0*MiscHelper.getMapLength(), 264.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(96.0/700.0*MiscHelper.getMapLength(), 435.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(605.0/700.0*MiscHelper.getMapLength(), 435.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(605.0/700.0*MiscHelper.getMapLength(), 302.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(585.0/700.0*MiscHelper.getMapLength(), 302.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(585.0/700.0*MiscHelper.getMapLength(), 414.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(114.0/700.0*MiscHelper.getMapLength(), 414.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(114.0/700.0*MiscHelper.getMapLength(), 96.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(585.0/700.0*MiscHelper.getMapLength(), 96.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(585.0/700.0*MiscHelper.getMapLength(), 206.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(605.0/700.0*MiscHelper.getMapLength(), 206.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(605.0/700.0*MiscHelper.getMapLength(), 76.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(96.0/700.0*MiscHelper.getMapLength(), 76.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(96.0/700.0*MiscHelper.getMapLength(), 246.0/700.0*MiscHelper.getMapLength()));
		path1.add(new Coordinate(-10.0, 246.0/700.0*MiscHelper.getMapLength()));
		addPath(path1);
	}
}