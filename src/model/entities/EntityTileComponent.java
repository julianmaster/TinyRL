package model.entities;

import pattern.Component;
import pattern.Event;

public class EntityTileComponent implements Component {
	
	private EntityTile entityTile;
	
	public EntityTileComponent(EntityTile entityTile) {
		this.entityTile = entityTile;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeEntityTileEvent) {
			ChangeEntityTileEvent changeEntityTileEvent = (ChangeEntityTileEvent)e;
			
			entityTile = changeEntityTileEvent.getNewEntityTile();
		}
	}

	public EntityTile getEntityTile() {
		return entityTile;
	}
}
