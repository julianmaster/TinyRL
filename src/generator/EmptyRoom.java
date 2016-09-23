package generator;

import model.Cell;
import model.Ground;
import model.Room;
import util.Pair;

public class EmptyRoom extends BaseRoom {

	@Override
	public void apply(Room room) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Cell cell = new Cell(null, null, Ground.DIRT);
				
				if(x % (Room.ROOM_SIZE-1) == 0 || y % (Room.ROOM_SIZE-1) == 0) {
					cell.setEntity(EntityGenerator.newWall());
				}
				
				room.setCell(new Pair<Integer, Integer>(x, y), cell);
			}
		}
		
		super.placeDoor(room);
	}
}
