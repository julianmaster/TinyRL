package model;

import java.awt.Color;

import ui.CustomColor;

public class Tile {
	public static final Tile PLAYER = new Tile('@', "You", CustomColor.WHITE);
	public static final Tile WALL = new Tile('#', "A wall", CustomColor.BROWN);
	public static final Tile CLOSE_DOOR = new Tile('+', "A close door", CustomColor.LGRAY);
	public static final Tile OPEN_DOOR = new Tile('/', "A open door", CustomColor.DGRAY);
	
	public char tile;
	public String name;
	public Color color;
	
	public Tile(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
