package model.particles;

import java.awt.Color;

import generator.EntityGenerator;
import main.TinyRL;
import model.PositionComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class ParticleEmitterComponent implements Component {
	
	private float duration;
	private float particlesPerSecond;
	private float speed;
	private float lifeLength;
	private Color color;
	
	private float elapsedTime = 0f;
	private float partialParticle = 0f;
	
	public ParticleEmitterComponent(float duration, float particlesPerSecond, float speed, float lifeLength, Color color) {
		this.duration = duration;
		this.particlesPerSecond = particlesPerSecond;
		this.speed = speed;
		this.lifeLength = lifeLength;
		this.color = color;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof UpdateParticleEmitterEvent) {
			Entity particleEmitter = Engine.getInstance().getEntityByComponent(this);
			
			elapsedTime += deltaTime;
			if(elapsedTime >= TinyRL.TARGET_FPS * duration) {
				System.out.println(elapsedTime);
				Engine.getInstance().removeEntity(particleEmitter);
			}
			
			PositionComponent positionComponent = particleEmitter.getComponentByClass(PositionComponent.class);
			
			partialParticle += deltaTime * particlesPerSecond / 60;
			int count = (int)partialParticle;
			partialParticle %= 1;
			for(int i = 0; i < count; i++) {
				Float dirX = (EntityGenerator.rand.nextFloat() * 2f - 1f) * speed;
				Float dirY = (EntityGenerator.rand.nextFloat() * 2f - 1f) * speed;
				
				Pair<Float, Float> velocity = new Pair<Float, Float>(dirX, dirY);
				Particle particle = EntityGenerator.newParticle(positionComponent.getPosition(), velocity, lifeLength, color);
				Engine.getInstance().addHeadEvent(new AddParticleEvent(particle));
			}
		}
	}
}
