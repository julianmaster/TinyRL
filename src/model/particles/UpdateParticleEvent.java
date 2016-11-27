package model.particles;

import pattern.ComponentEvent;

public class UpdateParticleEvent extends ComponentEvent {

	public UpdateParticleEvent() {
		super(ParticleComponent.class);
	}
}
