package model.animations;

import java.util.List;
import java.util.Random;

import main.TinyRL;
import model.Room;
import model.animations.RainHandler.RainType;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class RainHandlerComponent implements Component {
	
	private RainType rainType;
	private double elapseTime = 0;
	private Random rand = new Random();
	
	public RainHandlerComponent(RainType rainType) {
		this.rainType = rainType;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RainHandlerEvent) {
			List<Entity> rains = Engine.getInstance().getEntityByComponentClass(RainComponent.class);

			if(rains.size() < rainType.size) {
				elapseTime += deltaTime;
				if(elapseTime >= rainType.waitTime) {
					elapseTime = 0;
					
					int xRainPosition = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
					Pair<Integer, Integer> position = new Pair<Integer, Integer>(xRainPosition, 0);
					TinyRL.getInstance().getWorld().getCurrentRoom().getCell(position).getAnimations().add(AnimationEntities.newRain(position));
				}
			}
		}
	}
}
