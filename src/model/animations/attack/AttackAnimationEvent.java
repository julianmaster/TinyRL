package model.animations.attack;

import pattern.ComponentEvent;

public class AttackAnimationEvent extends ComponentEvent {
	
	public AttackAnimationEvent() {
		super(AttackAnimationHandlerComponent.class);
	}
}
