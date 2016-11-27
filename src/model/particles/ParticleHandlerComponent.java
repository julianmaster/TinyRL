package model.particles;

import java.util.ArrayList;
import java.util.Collections;

import main.TinyRL;
import model.PositionComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import ui.AsciiTerminalDataCell;
import util.Pair;

public class ParticleHandlerComponent implements Component {
	
	public class ParticlesCell extends ArrayList<Particle> {
	}
	
	private ParticlesCell[][] particles = new ParticlesCell[TinyRL.WINDOW_WIDTH][TinyRL.WINDOW_HEIGHT];

	public ParticleHandlerComponent() {
		for(int i = 0; i < TinyRL.WINDOW_WIDTH; i++) {
			for(int j = 0; j < TinyRL.WINDOW_HEIGHT; j++) {
				particles[i][j] = new ParticlesCell();
			}
		}
	}
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof TickParticleHandlerEvent) {
			Engine.getInstance().addTailEvent(new UpdateParticleEvent());
			Engine.getInstance().addTailEvent(new RenderParticlesEvent());
		}
		else if(e instanceof RemoveParticleEvent) {
			Particle particle = ((RemoveParticleEvent) e).getParticleToRemove();
			PositionComponent positionComponent = particle.getComponentByClass(PositionComponent.class);
			Pair<Integer, Integer> position = positionComponent.getPosition();
			particles[position.key][position.value].remove(particle);
		}
		else if(e instanceof RenderParticlesEvent) {
			for(int i = 0; i < TinyRL.WINDOW_WIDTH; i++) {
				for(int j = 0; j < TinyRL.WINDOW_HEIGHT; j++) {
					if(!particles[i][j].isEmpty()) {
						Collections.sort(particles[i][j]);
						
						Particle particle = particles[i][j].get(0);
						ParticleComponent particleComponent = particle.getComponentByClass(ParticleComponent.class);
						
						AsciiTerminalDataCell asciiTerminalDataCell = TinyRL.getInstance().getAsciiPanel().readNext(i, j);
						TinyRL.getInstance().getAsciiPanel().write(i, j, asciiTerminalDataCell.data, asciiTerminalDataCell.dataColor, particleComponent.getColor());
					}
				}
			}
		}
		else if(e instanceof AddParticleEvent) {
			AddParticleEvent addParticleEvent = (AddParticleEvent)e;
			Particle particle = addParticleEvent.getParticle();
			PositionComponent positionComponent = particle.getComponentByClass(PositionComponent.class);
			Pair<Integer, Integer> position = positionComponent.getPosition();
			
			particles[position.key][position.value].add(particle);
			Engine.getInstance().addEntity(particle);
		}
		else if(e instanceof MoveParticleEvent) {
			MoveParticleEvent moveParticleEvent = (MoveParticleEvent)e;
			Pair<Integer, Integer> oldPosition = moveParticleEvent.getOldPosition();
			Particle particle = moveParticleEvent.getParticle();
			PositionComponent positionComponent = particle.getComponentByClass(PositionComponent.class);
			Pair<Integer, Integer> position = positionComponent.getPosition();
			
			particles[oldPosition.key][oldPosition.value].remove(particle);
			particles[position.key][position.value].add(particle);
		}
	}
}
