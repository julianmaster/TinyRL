package model.animations;

import java.util.Random;

import model.Room;
import pattern.Component;
import pattern.Entity;

public class AnimationEntities {
	
	private static Random rand = new Random();
	
	public static Entity newRain() {
		Animation rain = new Animation();
		
		int rainLife = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
		rain.add(new RainComponent(rainLife));
		rain.add(new RainAnimationComponent());
		rain.add(new AnimationLevelComponent(rainLife));
		
		return rain;
	}
}
