package model;

import java.awt.Color;

import ui.CustomColor;

public enum Ground {
	DIRT('.', "Dirt", CustomColor.DGRAY),
	GRASS1('.', "Grass", CustomColor.LGREEN),
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
