package model;

import java.awt.Color;

import ui.CustomColor;

public enum Tile {
	PLAYER('@', "You", CustomColor.WHITE),
	WALL('#', "A wall", CustomColor.OILED_CEDAR),
	TREE1((char)6, "A tree", CustomColor.ATLANTIS),
	TREE2((char)6, "A tree", CustomColor.CHRISTI),
	TREE3((char)6, "A tree", CustomColor.RAIN_FOREST),
	CLOSE_DOOR('+', "A close door", CustomColor.HEATHER),
	OPEN_DOOR('/', "A open door", CustomColor.DIM_GRAY);
	
	public char tile;
	public String name;
	public Color color;
	
	private Tile(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
