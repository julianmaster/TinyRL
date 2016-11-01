package model.items;

import pattern.Component;
import pattern.Event;

public class ItemRarityComponent implements Component {

	private ItemRarity ItemRarity;
	
	public ItemRarityComponent(model.items.ItemRarity itemRarity) {
		ItemRarity = itemRarity;
	}

	@Override
	public void process(Event e, double deltaTime) {
	}

	public ItemRarity getItemRarity() {
		return ItemRarity;
	}
}
