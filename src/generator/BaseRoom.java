package generator;

import util.Pair;
import model.Direction;
import model.Room;
import model.entities.Door;

public abstract class BaseRoom {
	public abstract void apply(Room room);
	
	public void placeDoor(Room room) {
		Pair<Integer, Integer> roomPosition = room.getPosition();
		Door northDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value - 1), Direction.NORTH);
		Door southDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value + 1), Direction.SOUTH);
		Door eastDoor = new Door(new Pair<Integer, Integer>(roomPosition.key - 1, roomPosition.value), Direction.EAST);
		Door westDoor = new Door(new Pair<Integer, Integer>(roomPosition.key + 1, roomPosition.value), Direction.WEST);
		
		room.getCell((Room.ROOM_SIZE - 1) / 2, 0).setEntity(northDoor);
		room.getCell((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1).setEntity(southDoor);
		room.getCell(0, (Room.ROOM_SIZE - 1) / 2).setEntity(westDoor);
		room.getCell(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2).setEntity(eastDoor);
	}
}
