package model;

import java.awt.Color;

import ui.CustomColor;

public enum Ground {
	STONE('.', "Stone", CustomColor.SMOKEY_ASH),
	DIRT('.', "Dirt", CustomColor.ROPE),
	GRASS1('.', "Grass", CustomColor.ELF_GREEN),
	GRASS2(',', "Grass", CustomColor.ELF_GREEN),
	HIGH_GRASS1('.', "High grass", CustomColor.DELL),
	HIGH_GRASS2(',', "High grass", CustomColor.DELL);
	
	public char tile;
	public String name;
	public Color color;

	private Ground(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
