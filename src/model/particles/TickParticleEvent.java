package model.particles;

import pattern.ComponentEvent;

public class TickParticleEvent extends ComponentEvent {

	public TickParticleEvent() {
		super(ParticleComponent.class);
	}
}
