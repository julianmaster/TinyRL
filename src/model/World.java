package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	private Room currentRoom;
	private TurnController controller;
	
	public World() {
		world = new HashMap<>();
		controller = new TurnController();
		init();
	}

	private void init() {
		loadRoom(0, 0);
	}
	
	public void run() {
		controller.process();
		currentRoom.draw();
	}
	
	public void loadRoom(int x, int y) {
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(x, y);
		Room room = world.get(position);
		if(room == null) {
			room = RoomGenerator.generateRoom(position);
			world.put(new Pair<Integer, Integer>(x, y), room);
		}
		currentRoom = room;
		
		// TurnController
		controller.removeAllEntities();
		for(int i = 0; i < Room.ROOM_SIZE; i++) {
			for(int j = 0; j < Room.ROOM_SIZE; j++) {
				Cell cell = currentRoom.getCell(i, j);
				if(cell.getEntity() != null && cell.getEntity().getEnergy() != null) {
					controller.addEntity(cell.getEntity());
				}
			}
		}
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
}
