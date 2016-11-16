package model.particles;

import pattern.Entity;

public class Particle extends Entity implements Comparable<Particle> {

	@Override
	public int compareTo(Particle o) {
		ParticleComponent particleComponent = this.getComponentByClass(ParticleComponent.class);
		ParticleComponent otherParticleComponent = o.getComponentByClass(ParticleComponent.class);
		return new Float(particleComponent.getLevel()).compareTo(new Float(otherParticleComponent.getLevel()));
	}
}
