package model.particles;

import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class ParticleHandlerComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		Engine.getInstance().addHeadEvent(new TickParticleEvent());
	}
}
