package model.particles;

import generator.EntityGenerator;
import main.TinyRL;
import model.PositionComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class ParticleEmitterComponent implements Component {
	
	public float duration;
	public float particlesPerSecond;
	public float speed;
	public float lifeLength;
	
	private float elapsedTime = 0f;
	private float partialParticle = 0f;
	
	public ParticleEmitterComponent(float duration, float particlesPerSecond, float speed, float lifeLength) {
		super();
		this.duration = duration;
		this.particlesPerSecond = particlesPerSecond;
		this.speed = speed;
		this.lifeLength = lifeLength;
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
				Particle particle = EntityGenerator.newParticle(positionComponent.getPosition(), velocity, lifeLength);
				Engine.getInstance().addHeadEvent(new AddParticleEvent(particle));
			}
		}
	}
}
