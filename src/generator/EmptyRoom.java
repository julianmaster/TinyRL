package generator;

import model.Ground;
import model.Room;

public class EmptyRoom extends BaseRoom {

	@Override
	public void apply(Room room) {
		super.initRoom(room, Ground.STONE);
		super.placeWall(room);
		super.placeDoor(room);
	}
}
