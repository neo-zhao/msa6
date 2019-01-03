package msa6;

public class Main {
	public static void main(String[] args) {
		Map brickWall = new Map();
		Coordinate pathStart = brickWall.getPath().getFirst();
		
		brickWall.addBloon(new BloonRed(pathStart, ));
	}
}
