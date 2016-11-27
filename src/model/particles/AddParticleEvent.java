package model.particles;

import pattern.ComponentEvent;

public class AddParticleEvent extends ComponentEvent {
	
	private Particle particle;

	public AddParticleEvent(Particle particle) {
		super(ParticleHandlerComponent.class);
		this.particle = particle;
	}
	
	public Particle getParticle() {
		return particle;
	}
}
