package model.turns;

import model.entities.ModelEntity;
import pattern.ComponentEvent;
import pattern.Entity;

public class TurnControllerAddEntityEvent extends ComponentEvent {
	
	private ModelEntity entity;

	public TurnControllerAddEntityEvent(ModelEntity entity) {
		super(TurnControllerComponent.class);
		this.entity = entity;
	}
	
	public ModelEntity getEntity() {
		return entity;
	}
}
