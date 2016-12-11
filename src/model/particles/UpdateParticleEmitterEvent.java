package model.particles;

import pattern.ComponentEvent;

public class UpdateParticleEmitterEvent extends ComponentEvent {

	public UpdateParticleEmitterEvent() {
		super(ParticleEmitterComponent.class);
	}
}
