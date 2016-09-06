package model.animations;

import java.util.Random;

import model.Room;
import model.animations.RainHandler.RainType;
import pattern.Engine;
import pattern.Entity;
import util.Pair;

public class AnimationEntities {
	
	private static Random rand = new Random();
	
	public static Entity newRainHandler(RainType rainType) {
		Entity rainHandler = new Entity();
		
		rainHandler.add(new RainHandlerComponent(rainType));

		Engine.getInstance().addEntity(rainHandler);
		return rainHandler;
	}
	
	public static Animation newRain(Pair<Integer, Integer> position) {
		Animation rain = new Animation();
		
		int rainLife = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
		rain.add(new RainComponent(rainLife));
		rain.add(new PositionComponent(position));
		rain.add(new RainAnimationTileComponent());
		rain.add(new AnimationLevelComponent(rainLife));
		
		Engine.getInstance().addEntity(rain);
		return rain;
	}
}
