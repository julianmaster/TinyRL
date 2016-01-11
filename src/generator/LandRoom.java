package generator;

import java.util.ArrayList;
import java.util.Random;

import util.Pair;

import model.Cell;
import model.Ground;
import model.Room;
import model.entities.Wall;

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
				Cell cell = new Cell(null, null, null, ground);
				
				if(x % (Room.ROOM_SIZE-1) == 0 || y % (Room.ROOM_SIZE-1) == 0) {
					cell.setEntity(new Wall());
				}
				
				room.setCell(new Pair<Integer, Integer>(x, y), cell);
			}
		}
		
		super.placeDoor(room);
	}
}
