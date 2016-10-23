package model.animations;

import java.awt.Color;

import ui.CustomColor;

public class AnimationTile {
	public final static AnimationTile RAIN1 = new AnimationTile('|', CustomColor.VIKING);
	public final static AnimationTile RAIN2 = new AnimationTile((char)249, CustomColor.VIKING);
	public final static AnimationTile RAIN3 = new AnimationTile('o', CustomColor.VIKING);
	public final static AnimationTile RAIN4 = new AnimationTile((char)248, CustomColor.VIKING);
	public final static AnimationTile RAIN5 = new AnimationTile((char)250, CustomColor.VIKING);
	
	public final static AnimationTile ATTACK = new AnimationTile(' ', CustomColor.BROWN);
	
	public char tile;
	public Color color;

	public AnimationTile(char tile, Color color) {
		this.tile = tile;
		this.color = color;
	}
}
