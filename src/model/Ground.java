package model;

import java.awt.Color;

import ui.CustomColor;

public class Ground {
	public static final Ground DIRT = new Ground('.', "Dirt", CustomColor.DGRAY);
	public static final Ground GRASS = new Ground(',', "Grass", CustomColor.LGREEN);
	public static final Ground HIGH_GRASS = new Ground(',', "High grass", CustomColor.GREEN);
	
	public char tile;
	public String name;
	public Color color;

	public Ground(char tile, String name, Color color) {
		this.tile = tile;
		this.name = name;
		this.color = color;
	}
}
