package model.turns.actions;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class OpenActionEvent extends EntityComponentEvent {

	public OpenActionEvent(Entity entity) {
		super(OpenActionComponent.class, entity);
	}
}
