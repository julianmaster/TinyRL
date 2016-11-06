package model.items;

import java.awt.Color;

import ui.CustomColor;

public class ItemRarity {
	public static final ItemRarity USELESS_ITEM = new ItemRarity(0, CustomColor.HEATHER);
	public static final ItemRarity COMMUN_ITEM = new ItemRarity(1, CustomColor.WHITE);
	public static final ItemRarity MAGIC_ITEM = new ItemRarity(2, CustomColor.HEATHER);
	public static final ItemRarity RARE_ITEM = new ItemRarity(3, Color.RED, true);

	public int level;
	public Color color;
	private boolean changeColor = false;

	private ItemRarity(int level, Color color) {
		this.level = level;
		this.color = color;
	}

	private ItemRarity(int level, Color color, boolean changeColor) {
		this.level = level;
		this.color = color;
		this.changeColor = changeColor;
	}
	
	public void setColor(Color color) {
		if(changeColor) {
			this.color = color;
		}
	}
}
