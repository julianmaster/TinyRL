package model.items;

import pattern.Entity;

public class Item extends Entity implements Comparable<Item> {

	@Override
	public int compareTo(Item o) {
		ItemRarityComponent itemRarityComponent = this.getComponentByClass(ItemRarityComponent.class);
		ItemRarityComponent otherItemRarityComponent = o.getComponentByClass(ItemRarityComponent.class);
		
		return otherItemRarityComponent.getItemRarity().level -itemRarityComponent.getItemRarity().level;
	}
}
