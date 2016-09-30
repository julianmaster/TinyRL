package model.turns;

import pattern.ComponentEvent;
import pattern.Entity;

public class TurnControllerAddEntityEvent extends ComponentEvent {
	
	private Entity entity;

	public TurnControllerAddEntityEvent(Entity entity) {
		super(TurnControllerComponent.class);
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
}
