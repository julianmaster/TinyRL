package generator;

import model.Cell;
import model.Direction;
import model.Ground;
import model.Room;
import pattern.Entity;
import util.Pair;

public abstract class BaseRoom {
	public abstract void apply(Room room);
	
	public void initRoom(Room room, Ground ground) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Cell cell = new Cell(null, null, ground);
				room.setCell(new Pair<Integer, Integer>(x, y), cell);
			}
		}
	}
	
	public void placeWall(Room room) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			room.getCell(new Pair<Integer, Integer>(x, 0)).setEntity(EntityGenerator.newWall());
			room.getCell(new Pair<Integer, Integer>(x, Room.ROOM_SIZE-1)).setEntity(EntityGenerator.newWall());
		}
		
		for(int y = 1; y < Room.ROOM_SIZE-1; y++) {
			room.getCell(new Pair<Integer, Integer>(0, y)).setEntity(EntityGenerator.newWall());
			room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE-1, y)).setEntity(EntityGenerator.newWall());
		}
	}
	
	public void placeDoor(Room room) {
		Pair<Integer, Integer> roomPosition = room.getPosition();
		
		Pair<Integer, Integer> northDoorPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0);
		Pair<Integer, Integer> southDoorPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1);
		Pair<Integer, Integer> eastDoorPosition = new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2);
		Pair<Integer, Integer> westDoorPosition = new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2);
		
		Entity northDoor = EntityGenerator.newDoor(northDoorPosition, new Pair<Integer, Integer>(roomPosition.key, roomPosition.value - 1), Direction.N);
		Entity southDoor = EntityGenerator.newDoor(southDoorPosition, new Pair<Integer, Integer>(roomPosition.key, roomPosition.value + 1), Direction.S);
		Entity eastDoor = EntityGenerator.newDoor(eastDoorPosition, new Pair<Integer, Integer>(roomPosition.key - 1, roomPosition.value), Direction.E);
		Entity westDoor = EntityGenerator.newDoor(westDoorPosition, new Pair<Integer, Integer>(roomPosition.key + 1, roomPosition.value), Direction.W);
		
		room.getCell(northDoorPosition).setEntity(northDoor);
		room.getCell(southDoorPosition).setEntity(southDoor);
		room.getCell(eastDoorPosition).setEntity(westDoor);
		room.getCell(westDoorPosition).setEntity(eastDoor);
	}
}
