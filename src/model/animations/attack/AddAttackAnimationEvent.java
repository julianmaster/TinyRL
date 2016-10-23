package model.animations.attack;

import pattern.ComponentEvent;
import pattern.Entity;
import util.Pair;

public class AddAttackAnimationEvent extends ComponentEvent {
	
	private Entity attackedEntity;

	public AddAttackAnimationEvent(Entity attackedEntity) {
		super(AttackAnimationHandlerComponent.class);
		this.attackedEntity = attackedEntity;
	}

	public Entity getAttackedEntity() {
		return attackedEntity;
	}
}
