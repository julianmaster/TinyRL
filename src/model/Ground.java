package model;

import java.awt.Color;

import ui.CustomColor;

public class Ground {
	public static final Ground DIRT = new Ground("Dirt", '.', CustomColor.DGRAY);
	
	public String name;
	public char tile;
	public Color color;

	public Ground(String name, char tile, Color color) {
		this.name = name;
		this.tile = tile;
		this.color = color;
	}
}
