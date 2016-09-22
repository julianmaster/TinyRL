package model.entities;

import model.Tile;
import pattern.Component;
import pattern.Event;

public class EntityTileComponent implements Component {
	
	private Tile tile;
	
	public EntityTileComponent(Tile tile) {
		this.tile = tile;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeEntityTileEvent) {
			ChangeEntityTileEvent changeEntityTileEvent = (ChangeEntityTileEvent)e;
			
			tile = changeEntityTileEvent.getNewTile();
		}
	}
	
	public Tile getTile() {
		return tile;
	}
}
