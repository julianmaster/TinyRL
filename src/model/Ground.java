package model;

import java.awt.Color;

import ui.CustomColor;

public enum Ground {
	STONE('.', "Stone", CustomColor.DGRAY),
	DIRT('.', "Dirt", CustomColor.BROWN),
	GRASS1('.', "Grass", CustomColor.GREEN),
	GRASS2(',', "Grass", CustomColor.LGREEN),
	HIGH_GRASS1('.', "High grass", CustomColor.GREEN),
	HIGH_GRASS2(',', "High grass", CustomColor.GREEN);
	
	public char tile;
	public String name;
	public Color color;

	private Ground(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
