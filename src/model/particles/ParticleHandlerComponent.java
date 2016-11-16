package model.particles;

import java.util.ArrayList;
import java.util.List;

import main.TinyRL;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class ParticleHandlerComponent implements Component {
	
	public class ParticlesCell {
		public List<Particle> entities = new ArrayList<Particle>();
	}
	
	private ParticlesCell[][] particles = new ParticlesCell[TinyRL.WINDOW_WIDTH][TinyRL.WINDOW_HEIGHT];

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RenderParticlesEvent) {
			
		}
//		Engine.getInstance().getEntitiesByComponentClass(Pa)
//		Engine.getInstance().addHeadEvent(new TickParticleEvent());
	}
}
