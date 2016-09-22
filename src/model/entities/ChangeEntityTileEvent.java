package model.entities;

import model.Tile;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class ChangeEntityTileEvent extends EntityComponentEvent {

	private Tile newTile;

	public ChangeEntityTileEvent(Entity entity, Tile newTile) {
		super(EntityTileComponent.class, entity);
		this.newTile = newTile;
	}
	
	public Tile getNewTile() {
		return newTile;
	}
}
