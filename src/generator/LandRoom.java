package generator;

import java.util.ArrayList;
import java.util.Random;

import model.Ground;
import model.Room;
import model.animations.rain.RainHandlerComponent.RainType;
import util.Pair;

public class LandRoom extends BaseRoom {

	private Random rand = new Random();
	private static ArrayList<Ground> grounds = new ArrayList<Ground>() {{
		add(Ground.GRASS1);
		add(Ground.GRASS2);
		add(Ground.HIGH_GRASS1);
		add(Ground.HIGH_GRASS2);
	}};
	
	@Override
	public void apply(Room room) {
		super.initRoom(room, Ground.DIRT);
		super.placeWall(room);
		super.placeDoor(room);
		
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Ground ground = grounds.get(rand.nextInt(grounds.size()));
				room.getCell(new Pair<Integer, Integer>(x, y)).setGround(ground);
			}
		}
		
		if(rand.nextBoolean()) {
			int rain = rand.nextInt(2);
			RainType rainType = null;
			switch (rain) {
				case 0:
					rainType = RainType.TINY_RAIN;
					break;
				case 1:
					rainType = RainType.BIG_RAIN;
					break;
			}
			room.getAnimationHandlers().add(EntityGenerator.newRainHandler(rainType));
		}
	}
}
