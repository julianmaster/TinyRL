package model.animations.rain;

import java.util.List;
import java.util.Random;

import main.TinyRL;
import model.Room;
import model.animations.Animation;
import model.animations.AnimationEntities;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class RainHandlerComponent implements Component {
	
	public enum RainType {
		TINY_RAIN(5, 6),
		MEDIUM_RAIN(15, 3.2),
		BIG_RAIN(30, 1.5);
		
		public int size;
		public double waitTime;
		
		private RainType(int size, double waitTime) {
			this.size = size;
			this.waitTime = waitTime;
		}
	}
	
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
			if(Engine.DEBUG) {
				Engine engine = Engine.getInstance();
				System.out.println("Error - "+this.getClass().getName());
				Engine.DEBUG = false;
			}

			if(rains.size() < rainType.size) {
				elapseTime += deltaTime;
				if(elapseTime >= rainType.waitTime) {
					elapseTime = 0;
					
					int xRainPosition = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
					Pair<Integer, Integer> position = new Pair<Integer, Integer>(xRainPosition, 0);
					Animation animation = AnimationEntities.newRain(position);
					Engine.getInstance().addEntity(animation);
					TinyRL.getInstance().getWorld().getCurrentRoom().getCell(position).getAnimations().add(animation);
				}
			}
			
			Engine.getInstance().addTailEvent(new RainEvent());
		}
	}
}
