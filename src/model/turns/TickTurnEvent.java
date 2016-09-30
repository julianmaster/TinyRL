package model.turns;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class TickTurnEvent extends EntityComponentEvent {

	public TickTurnEvent(Entity entity) {
		super(TurnComponent.class, entity);
	}
}
