package generator;

import util.Pair;
import model.Direction;
import model.Room;
import model.entities.Door;
import model.entities.ModelEntities;

public abstract class BaseRoom {
	public abstract void apply(Room room);
	
	public void placeDoor(Room room) {
		Pair<Integer, Integer> roomPosition = room.getPosition();
		
		Pair<Integer, Integer> northDoorPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0);
		Pair<Integer, Integer> southDoorPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1);
		Pair<Integer, Integer> eastDoorPosition = new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2);
		Pair<Integer, Integer> westDoorPosition = new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2);
		
//		Door northDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value - 1), Direction.N);
//		Door southDoor = new Door(new Pair<Integer, Integer>(roomPosition.key, roomPosition.value + 1), Direction.S);
//		Door eastDoor = new Door(new Pair<Integer, Integer>(roomPosition.key - 1, roomPosition.value), Direction.E);
//		Door westDoor = new Door(new Pair<Integer, Integer>(roomPosition.key + 1, roomPosition.value), Direction.W);
		
		Door northDoor = ModelEntities.newDoor(northDoorPosition, new Pair<Integer, Integer>(roomPosition.key, roomPosition.value - 1), Direction.N);
		Door southDoor = ModelEntities.newDoor(southDoorPosition, new Pair<Integer, Integer>(roomPosition.key, roomPosition.value + 1), Direction.S);
		Door eastDoor = ModelEntities.newDoor(eastDoorPosition, new Pair<Integer, Integer>(roomPosition.key - 1, roomPosition.value), Direction.E);
		Door westDoor = ModelEntities.newDoor(westDoorPosition, new Pair<Integer, Integer>(roomPosition.key + 1, roomPosition.value), Direction.W);
		
		room.getCell(northDoorPosition).setEntity(northDoor);
		room.getCell(southDoorPosition).setEntity(southDoor);
		room.getCell(eastDoorPosition).setEntity(westDoor);
		room.getCell(westDoorPosition).setEntity(eastDoor);
	}
}
