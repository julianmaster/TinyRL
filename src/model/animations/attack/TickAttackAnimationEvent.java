package model.animations.attack;

import pattern.ComponentEvent;

public class TickAttackAnimationEvent extends ComponentEvent{

	public TickAttackAnimationEvent() {
		super(AttackAnimationComponent.class);
	}
}
