package msa6;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		//setting up the Model
		LinkedList<Double> eventList = new LinkedList<Double>();
		
		//setting up the map
		Map brickWall = new MapBrickWall();
		Coordinate pathStart = brickWall.getPaths().get(0).get(0);
		LinkedList<Coordinate> path = brickWall.getPaths().get(0);
	
		//adding towers
		brickWall.addTower(new DartMonkey00(new Coordinate(169, 259), "first"));
		
		//adding bloons
		eventList.add();
		brickWall.addBloon(new RedBloon(pathStart, path));
		
		//run simulation
		while (!eventList.isEmpty()) {
			//jump to next event time
			//move bloons (check if any make it to the end of the map)
			//update ranks of the bloons
			//update location of projectiles and pop bloons (remove used projectiles and popped bloons, add resulting bloons)
			//add new events and sorts the event list
		}
	}
}
