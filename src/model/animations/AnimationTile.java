package model.animations;

import java.awt.Color;

import ui.CustomColor;

public enum AnimationTile {
	RAIN1('|', CustomColor.LBLUE),
	RAIN2((char)249, CustomColor.LBLUE),
	RAIN3('o', CustomColor.LBLUE),
	RAIN4((char)248, CustomColor.LBLUE),
	RAIN5((char)250, CustomColor.LBLUE);
	
	public char tile;
	public Color color;

	private AnimationTile(char tile, Color color) {
		this.tile = tile;
		this.color = color;
	}
}
