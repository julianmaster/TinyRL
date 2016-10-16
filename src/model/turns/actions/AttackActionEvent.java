package model.turns.actions;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class AttackActionEvent extends EntityComponentEvent {
	
	private Entity entityToAttack;

	public AttackActionEvent(Entity entity, Entity entityToAttack) {
		super(AttackActionComponent.class, entity);
		this.entityToAttack = entityToAttack;
	}
	
	public Entity getEntityToAttack() {
		return entityToAttack;
	}
}
