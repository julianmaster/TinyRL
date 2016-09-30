package model.turns.actions;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class ChangeRoomActionEvent extends EntityComponentEvent {
	
	private Entity entityToMove;

	public ChangeRoomActionEvent(Entity entity, Entity entityToMove) {
		super(ChangeRoomActionComponent.class, entity);
		this.entityToMove = entityToMove;
	}

	public Entity getEntityToMove() {
		return entityToMove;
	}
}
