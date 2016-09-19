package model.animations;

import java.util.Random;

import model.Room;
import model.PositionComponent;
import model.animations.rain.RainAnimationTileComponent;
import model.animations.rain.RainComponent;
import model.animations.rain.RainHandlerComponent;
import model.animations.rain.RainHandlerComponent.RainType;
import pattern.Entity;
import util.Pair;

public class AnimationEntities {
	
	private static Random rand = new Random();
	
	public static Entity newRainHandler(RainType rainType) {
		Entity rainHandler = new Entity();
		
		rainHandler.add(new RainHandlerComponent(rainType));

		return rainHandler;
	}
	
	public static Animation newRain(Pair<Integer, Integer> position) {
		Animation rain = new Animation();
		
		int rainLife = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
		rain.add(new RainComponent(rainLife));
		rain.add(new PositionComponent(position));
		rain.add(new RainAnimationTileComponent());
		rain.add(new AnimationLevelComponent(rainLife));
		
		return rain;
	}
}
