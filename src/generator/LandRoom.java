package generator;

import java.util.ArrayList;
import java.util.Random;

import model.Cell;
import model.Ground;
import model.Room;
import model.animations.rain.RainHandlerComponent.RainType;
import util.Pair;

public class LandRoom extends BaseRoom {

	Random rand = new Random();
	private static ArrayList<Ground> grounds = new ArrayList<Ground>() {{
		add(Ground.GRASS1);
		add(Ground.GRASS2);
		add(Ground.HIGH_GRASS1);
		add(Ground.HIGH_GRASS2);
	}};
	
	@Override
	public void apply(Room room) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Ground ground = grounds.get(rand.nextInt(grounds.size()));
				Cell cell = new Cell(null, null, ground);
				
				if(x % (Room.ROOM_SIZE-1) == 0 || y % (Room.ROOM_SIZE-1) == 0) {
					cell.setEntity(EntityGenerator.newWall());
				}
				
				room.setCell(new Pair<Integer, Integer>(x, y), cell);
			}
		}
		
		if(rand.nextBoolean()) {
			int rain = rand.nextInt(3);
			RainType rainType = null;
			switch (rain) {
				case 0:
					rainType = RainType.TINY_RAIN;
					break;
				case 1:
					rainType = RainType.MEDIUM_RAIN;
					break;
				case 2:
					rainType = RainType.BIG_RAIN;
					break;
			}
			System.out.println("RainType: "+rainType);
			room.getAnimationHandlers().add(EntityGenerator.newRainHandler(rainType));
		}
		
		super.placeDoor(room);
	}
}
