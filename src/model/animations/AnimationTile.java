package model.animations;

import java.awt.Color;

import ui.CustomColor;

public enum AnimationTile {
	RAIN1('|', CustomColor.BLUE),
	RAIN2('O', CustomColor.BLUE);
	
	public char tile;
	public Color color;

	private AnimationTile(char tile, Color color) {
		this.tile = tile;
		this.color = color;
	}
}
