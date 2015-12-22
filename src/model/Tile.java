package model;

public class Tile {
	public static final Tile PLAYER = new Tile("You", '@');
	
	public String name;
	public char tile;
	
	private Tile(String name, char tile) {
		this.name = name;
		this.tile = tile;
	}
}
