package msa6;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import bloons.RedBloon;
import framework.GameMap;
import framework.MiscHelper;
import map.BrickWallMap;
import towers.DartMonkey00;

public class Main {
	
	/**
	 * Main function (The Sim is run in here)
	 * @param args not used
	 */
	public static void main(String[] args) {
		int livesLost = 0;
		LinkedList<Double> eventList = new LinkedList<Double>();
		//create map
		GameMap brickWall = new BrickWallMap();
		
		//add Towers
		brickWall.addTower(new DartMonkey00(new Point2D.Double(205/700*MiscHelper.getMapLength(), MiscHelper.getMapHeight()/2), MiscHelper.getPriorityFirst()));
		
		//add Bloons
		brickWall.addBloon(new RedBloon(0));
		
		//set up Sim
		
		//Run Sim
		while(!eventList.isEmpty()) {
			
		}
		
		System.out.println("Lives Lost: " + livesLost);
	}
	
}