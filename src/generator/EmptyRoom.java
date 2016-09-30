package generator;

import model.Cell;
import model.Ground;
import model.Room;
import util.Pair;

public class EmptyRoom extends BaseRoom {

	@Override
	public void apply(Room room) {
		super.initRoom(room, Ground.STONE);
		super.placeWall(room);
		super.placeDoor(room);
	}
}
