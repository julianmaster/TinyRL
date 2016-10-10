package model;

import java.awt.Color;

import ui.CustomColor;

public enum Tile {
	WALL('#', "A wall", CustomColor.TOPAZ),
	TREE1((char)6, "A tree", CustomColor.ATLANTIS),
	TREE2((char)6, "A tree", CustomColor.CHRISTI),
	TREE3((char)6, "A tree", CustomColor.RAIN_FOREST),
	CLOSE_DOOR('+', "A close door", CustomColor.OILED_CEDAR),
	OPEN_DOOR('/', "A open door", CustomColor.LOULOU),
	
	PLAYER('@', "You", CustomColor.WHITE),
	SKELETON('s', "A skeleton", CustomColor.WHITE),
	ARCHER_SKELETON('a', "A archer skeleton", CustomColor.WHITE),
	GUARD_SKELETON('g', "A guard skeleton", CustomColor.WHITE);
	
	public char tile;
	public String name;
	public Color color;
	
	private Tile(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
