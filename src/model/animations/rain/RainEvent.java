package model.animations.rain;

import pattern.Component;
import pattern.ComponentEvent;

public class RainEvent extends ComponentEvent {

	public RainEvent() {
		super(RainComponent.class);
	}

}