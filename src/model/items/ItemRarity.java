package model.items;

import java.awt.Color;

import ui.CustomColor;

public enum ItemRarity {
	USELESS_ITEM(0, CustomColor.HEATHER),
	COMMUN_ITEM(1, CustomColor.WHITE),
	MAGIC_ITEM(2, CustomColor.HEATHER),
	RARE_ITEM(3, CustomColor.HEATHER);

	public int level;
	public Color color;

	private ItemRarity(int level, Color color) {
		this.level = level;
		this.color = color;
	}
}
