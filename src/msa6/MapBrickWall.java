package msa6;

import java.util.LinkedList;

public class MapBrickWall extends GameMap{
	//variable declarations
	
	/**
	 * constructor
	 */
	public MapBrickWall() {
		super(new LinkedList<LinkedList<Coordinate>>());
		//adds the one path on the map
		super.paths.add(new LinkedList<Coordinate>());
		//adding the turns (in order) assuming map is 37 by 27 (based off the diagram)
		super.paths.get(0).add(new Coordinate(0, 264));
		super.paths.get(0).add(new Coordinate(94, 264));
		super.paths.get(0).add(new Coordinate(94, 434));
		super.paths.get(0).add(new Coordinate(605, 434));
		super.paths.get(0).add(new Coordinate(605, 302));
		super.paths.get(0).add(new Coordinate(585, 302));
		super.paths.get(0).add(new Coordinate(585, 414));
		super.paths.get(0).add(new Coordinate(114, 414));
		super.paths.get(0).add(new Coordinate(114, 96));
		super.paths.get(0).add(new Coordinate(585, 96));
		super.paths.get(0).add(new Coordinate(585, 206));
		super.paths.get(0).add(new Coordinate(605, 206));
		super.paths.get(0).add(new Coordinate(605, 76));
		super.paths.get(0).add(new Coordinate(94, 76));
		super.paths.get(0).add(new Coordinate(94, 244));
		super.paths.get(0).add(new Coordinate(0, 244));
	}
	
}
