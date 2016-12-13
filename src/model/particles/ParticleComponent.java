package model.particles;

import java.awt.Color;

import main.TinyRL;
import model.ChangePositionEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class ParticleComponent implements Component {
	private Pair<Float, Float> position;
	private Pair<Float, Float> velocity;
	private float lifeLength;
	private Color color;
	
	private float elapsedTime = 0;
	
	public ParticleComponent(Pair<Float, Float> position, Pair<Float, Float> velocity, float lifeLength, Color color) {
		this.position = position;
		this.velocity = velocity;
		this.lifeLength = lifeLength;
		this.color = color;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof UpdateParticleEvent) {
			Particle particle = (Particle)Engine.getInstance().getEntityByComponent(this);
			
			elapsedTime += deltaTime;
			if(elapsedTime >= lifeLength) {
				Engine.getInstance().addHeadEvent(new RemoveParticleEvent(particle));
				Engine.getInstance().removeEntity(particle);
			}
			
			Pair<Integer, Integer> oldPosition = new Pair<Integer, Integer>(position.key.intValue(), position.value.intValue());
			
			Pair<Float, Float> change = new Pair<Float, Float>(velocity.key, velocity.value);
			change.key *= (float)deltaTime;
			change.value *= (float)deltaTime;
			position.key += change.key;
			position.value += change.value;
			if(oldPosition.key != position.key.intValue() || oldPosition.value != position.value.intValue()) {
				if(position.key >= 0 && position.key < TinyRL.WINDOW_WIDTH && position.value >= 0 && position.value < TinyRL.WINDOW_HEIGHT) {
					Engine.getInstance().addHeadEvent(new MoveParticleEvent(oldPosition, particle));
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(particle, position.key.intValue(), position.value.intValue()));
				}
				else {
					Engine.getInstance().addHeadEvent(new RemoveParticleEvent(particle));
					Engine.getInstance().removeEntity(particle);
				}
			}
		}
	}
	
	public float getLevel() {
		return (lifeLength - elapsedTime) / lifeLength;
	}
	
	public Color getColor() {
		int r = (int)(color.getRed() * (lifeLength - elapsedTime) / lifeLength);
		int v = (int)(color.getGreen() * (lifeLength - elapsedTime) / lifeLength);
		int b = (int)(color.getBlue() * (lifeLength - elapsedTime) / lifeLength);
		
		return new Color(r,v,b);
	}
}
