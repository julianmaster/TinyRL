package model.entities;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class ResolveTurnEvent extends EntityComponentEvent {

	public ResolveTurnEvent(Entity entity) {
		super(AttributesComponent.class, entity);
	}
}
