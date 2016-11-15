package model.particles;

import main.TinyRL;
import model.PositionComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class ParticleComponent implements Component {
	private Pair<Float, Float> position;
	private Pair<Float, Float> velocity;
	private float lifeLength;
	
	private float elapsedTime = 0;
	
	public ParticleComponent(Pair<Float, Float> position, Pair<Float, Float> velocity, float lifeLength) {
		this.position = position;
		this.velocity = velocity;
		this.lifeLength = lifeLength;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof TickParticleEvent) {
			Entity particle = Engine.getInstance().getEntityByComponent(this);
			PositionComponent positionComponent = particle.getComponentByClass(PositionComponent.class);
			
			Pair<Float, Float> change = new Pair<Float, Float>(velocity.key, velocity.value);
			change.key *= (float)deltaTime;
			change.value *= (float)deltaTime;
			position.key += change.key;
			position.value += change.value;
			
			elapsedTime += deltaTime;
			if(elapsedTime >= lifeLength) {
				
				Engine.getInstance().removeEntity(particle);
			}
		}
	}
}
