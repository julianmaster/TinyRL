package model;

import java.awt.Color;

import ui.CustomColor;

public class Tile {
	public static final Tile PLAYER = new Tile("You", '@', CustomColor.WHITE);
	public static final Tile WALL = new Tile("A wall", '#', CustomColor.BROWN);
	public static final Tile CLOSE_DOOR = new Tile("A close door", '+', CustomColor.LGRAY);
	public static final Tile OPEN_DOOR = new Tile("A open door", '/', CustomColor.DGRAY);
	
	public String name;
	public char tile;
	public Color color;
	
	public Tile(String name, char tile, Color color) {
		this.name = name;
		this.tile = tile;
		this.color = color;
	}
}
