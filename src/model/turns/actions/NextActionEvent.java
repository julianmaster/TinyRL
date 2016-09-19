package model.turns.actions;

import model.turns.TurnComponent;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class NextActionEvent extends EntityComponentEvent {
	
	private EntityComponentEvent lastActionEvent;

	public NextActionEvent(Entity entity, EntityComponentEvent lastActionEvent) {
		super(TurnComponent.class, entity);
		this.lastActionEvent = lastActionEvent;
	}
	
	public EntityComponentEvent getLastActionEvent() {
		return lastActionEvent;
	}
}
