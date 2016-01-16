package model.animations;

import java.awt.Color;

import ui.CustomColor;

public enum AnimationTile {
	RAIN1('|', CustomColor.LBLUE),
	RAIN2('X', CustomColor.LBLUE),
	RAIN3('O', CustomColor.LBLUE),
	RAIN4('o', CustomColor.LBLUE),
	RAIN5('.', CustomColor.LBLUE);
	
	public char tile;
	public Color color;

	private AnimationTile(char tile, Color color) {
		this.tile = tile;
		this.color = color;
	}
}
