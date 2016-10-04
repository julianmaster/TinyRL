package model.animations;

import java.awt.Color;

import ui.CustomColor;

public enum AnimationTile {
	RAIN1('|', CustomColor.VIKING),
	RAIN2((char)249, CustomColor.VIKING),
	RAIN3('o', CustomColor.VIKING),
	RAIN4((char)248, CustomColor.VIKING),
	RAIN5((char)250, CustomColor.VIKING);
	
	public char tile;
	public Color color;

	private AnimationTile(char tile, Color color) {
		this.tile = tile;
		this.color = color;
	}
}
