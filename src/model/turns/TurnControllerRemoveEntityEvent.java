package model.turns;

import pattern.ComponentEvent;
import pattern.Entity;

public class TurnControllerRemoveEntityEvent extends ComponentEvent {
	
	private Entity entityToRemove;

	public TurnControllerRemoveEntityEvent(Entity entityToRemove) {
		super(TurnControllerComponent.class);
		this.entityToRemove = entityToRemove;
	}
	
	public Entity getEntityToRemove() {
		return entityToRemove;
	}
}
