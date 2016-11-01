package model.items;

import pattern.Component;
import pattern.Event;

public class ItemTileComponent implements Component {
	
	private ItemTile itemTile;
	
	public ItemTileComponent(ItemTile itemTile) {
		super();
		this.itemTile = itemTile;
	}

	@Override
	public void process(Event e, double deltaTime) {
		
	}
	
	public ItemTile getItemTile() {
		return itemTile;
	}
}
