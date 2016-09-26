package model.animations.rain;

import java.util.List;
import java.util.Random;

import generator.EntityGenerator;
import model.Room;
import model.RoomComponent;
import model.animations.Animation;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class RainHandlerComponent implements Component {
	
	public enum RainType {
		TINY_RAIN(4, 6),
		BIG_RAIN(12, 3.2);
		
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
			List<Entity> rains = Engine.getInstance().getEntitiesByComponentClass(RainComponent.class);
			Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);

			if(rains.size() < rainType.size) {
				elapseTime += deltaTime;
				if(elapseTime >= rainType.waitTime) {
					elapseTime = 0;
					
					int xRainPosition = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
					Pair<Integer, Integer> position = new Pair<Integer, Integer>(xRainPosition, 0);
					Animation animation = EntityGenerator.newRain(position);
					Engine.getInstance().addEntity(animation);
					room.getCell(position).getAnimations().add(animation);
				}
			}
			
			Engine.getInstance().addTailEvent(new RainEvent());
		}
	}
}
