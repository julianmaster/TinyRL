package model.particles;

import pattern.ComponentEvent;

public class RenderParticlesEvent extends ComponentEvent {

	public RenderParticlesEvent() {
		super(ParticleHandlerComponent.class);
	}
}
