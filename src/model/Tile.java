package model;

import java.awt.Color;

import ui.CustomColor;

public enum Tile {
	PLAYER('@', "You", CustomColor.WHITE),
	WALL('#', "A wall", CustomColor.BROWN),
	TREE1((char)6, "A tree", CustomColor.GREEN),
	CLOSE_DOOR('+', "A close door", CustomColor.LGRAY),
	OPEN_DOOR('/', "A open door", CustomColor.DGRAY);
	
	public char tile;
	public String name;
	public Color color;
	
	private Tile(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
