package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import model.entities.Player;
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
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(0, 0);
		createRoom(position);
		Room room = getRoom(position);
		room.getCell(4, 4).setEntity(new Player());
		loadRoom(position);
	}
	
	public void run() {
		controller.process();
		currentRoom.draw();
	}
	
	public boolean createRoom(Pair<Integer, Integer> position) {
		Room room = world.get(position);
		if(room != null) {
			return false;
		}
		
		room = RoomGenerator.generateRoom(position);
		world.put(position, room);
		return true;
	}
	
	public boolean loadRoom(Pair<Integer, Integer> position) {
		Room room = world.get(position);
		if(room == null) {
			return false;
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
		return true;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Room getRoom(Pair<Integer, Integer> position) {
		return world.get(position);
	}
}
