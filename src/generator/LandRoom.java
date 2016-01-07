package generator;

import java.util.Random;

import model.Cell;
import model.Ground;
import model.Room;
import model.entities.Wall;

public class LandRoom extends BaseRoom {

	Random rand = new Random();
	
	@Override
	public void apply(Room room) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Cell cell = null;
				if(rand.nextBoolean()) {
					cell = new Cell(null, null, Ground.GRASS);
				}
				else {
					cell = new Cell(null, null, Ground.HIGH_GRASS);
				}
				
				if(x % (Room.ROOM_SIZE-1) == 0 || y % (Room.ROOM_SIZE-1) == 0) {
					cell.setEntity(new Wall());
				}
				
				room.setCell(x, y, cell);
			}
		}
		
		super.placeDoor(room);
	}
}
