package generator;

import util.Pair;
import model.Room;
import model.entities.Door;

public abstract class BaseRoom {
	public abstract void apply(Room room);
	
	public void placeDoor(Room room) {
		Pair<Integer, Integer> roomPosition = room.getPosition();
		Door northDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value - 1));
		Door southDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value + 1));
		Door eastDoor = new Door(new Pair<Integer, Integer>(roomPosition.key - 1, roomPosition.value));
		Door westDoor = new Door(new Pair<Integer, Integer>(roomPosition.key + 1, roomPosition.value));
		
		room.getCell(4, 0).setEntity(northDoor);
		room.getCell(4, 8).setEntity(southDoor);
		room.getCell(0, 4).setEntity(eastDoor);
		room.getCell(8, 4).setEntity(westDoor);
	}
}
