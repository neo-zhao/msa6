package msa6;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BrickWallMap extends GameMap {
	
	/**
	 * default constructor
	 */
	public BrickWallMap() {
		//applies super constructor
		super();
		
		//adds path
		ArrayList<Point2D.Double> path1 = new ArrayList<Point2D.Double>();
		path1.add(new Point2D.Double(0, 264/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(96/700*MiscHelper.getMapLength(), 264/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(96/700*MiscHelper.getMapLength(), 435/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(605/700*MiscHelper.getMapLength(), 435/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(605/700*MiscHelper.getMapLength(), 302/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(585/700*MiscHelper.getMapLength(), 302/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(585/700*MiscHelper.getMapLength(), 414/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(114/700*MiscHelper.getMapLength(), 414/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(114/700*MiscHelper.getMapLength(), 96/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(585/700*MiscHelper.getMapLength(), 96/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(585/700*MiscHelper.getMapLength(), 206/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(605/700*MiscHelper.getMapLength(), 206/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(605/700*MiscHelper.getMapLength(), 76/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(96/700*MiscHelper.getMapLength(), 76/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(96/700*MiscHelper.getMapLength(), 246/700*MiscHelper.getMapLength()));
		path1.add(new Point2D.Double(-10, 246/700*MiscHelper.getMapLength()));
		addPath(path1);
	}
}