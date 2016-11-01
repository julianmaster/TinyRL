package model.entities;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class ChangeEntityTileEvent extends EntityComponentEvent {

	private EntityTile newEntityTile;

	public ChangeEntityTileEvent(Entity entity, EntityTile newEntityTile) {
		super(EntityTileComponent.class, entity);
		this.newEntityTile = newEntityTile;
	}

	public EntityTile getNewEntityTile() {
		return newEntityTile;
	}
}
