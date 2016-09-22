package model.turns.actions;

import model.entities.ModelEntity;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class ChangeRoomActionEvent extends EntityComponentEvent {
	
	private ModelEntity entityToMove;

	public ChangeRoomActionEvent(Entity entity, ModelEntity entityToMove) {
		super(ChangeRoomActionComponent.class, entity);
		this.entityToMove = entityToMove;
	}

	public ModelEntity getEntityToMove() {
		return entityToMove;
	}
}
