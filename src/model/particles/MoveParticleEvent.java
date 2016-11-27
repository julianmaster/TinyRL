package model.particles;

import pattern.ComponentEvent;
import util.Pair;

public class MoveParticleEvent extends ComponentEvent {
	
	private Pair<Integer, Integer> oldPosition;
	private Particle particle;
	
	public MoveParticleEvent(Pair<Integer, Integer> oldPosition, Particle particle) {
		super(ParticleHandlerComponent.class);
		this.oldPosition = oldPosition;
		this.particle = particle;
	}
	
	public Pair<Integer, Integer> getOldPosition() {
		return oldPosition;
	}
	
	public Particle getParticle() {
		return particle;
	}
}
