package model.entities;

import model.entities.dead.DeadComponent;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class KillEvent extends EntityComponentEvent {

	public KillEvent(Entity entity) {
		super(DeadComponent.class, entity);
	}
}
