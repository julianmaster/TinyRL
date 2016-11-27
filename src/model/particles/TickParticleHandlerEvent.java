package model.particles;

import pattern.ComponentEvent;

public class TickParticleHandlerEvent extends ComponentEvent {

	public TickParticleHandlerEvent() {
		super(ParticleHandlerComponent.class);
	}
}
