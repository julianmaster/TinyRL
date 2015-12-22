package model;

public class Ground {
	public static final Ground DIRT = new Tile("Dirt", '.');
	
	public String name;
	public char tile;

	private Ground(String name, char tile) {
		this.name = name;
		this.tile = tile;
	}
}
