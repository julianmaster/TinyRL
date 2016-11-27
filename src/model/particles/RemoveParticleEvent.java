package model.particles;

import pattern.ComponentEvent;

public class RemoveParticleEvent extends ComponentEvent {

	private Particle particleToRemove;
	
	public RemoveParticleEvent(Particle particleToRemove) {
		super(ParticleHandlerComponent.class);
		this.particleToRemove = particleToRemove;
	}
	
	public Particle getParticleToRemove() {
		return particleToRemove;
	}
}
