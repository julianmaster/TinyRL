package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import model.entities.Player;
import model.turns.TurnController;
import pattern.Engine;
import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	private Room currentRoom;
	private TurnController turnController;
	
	public World() {
		world = new HashMap<>();
		turnController = new TurnController();
		init();
	}

	private void init() {
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(0, 0);
		createRoom(position);
		Room room = getRoom(position);
		Engine.getInstance().addEntities(room.getAnimationHandlers());
		Engine.getInstance().addEntities(room.getAnimations());
		room.getCell(new Pair<Integer, Integer>(4, 4)).setEntity(new Player());
		loadRoom(position);
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
		turnController.removeAllEntities();
		for(int i = 0; i < Room.ROOM_SIZE; i++) {
			for(int j = 0; j < Room.ROOM_SIZE; j++) {
				Cell cell = currentRoom.getCell(new Pair<Integer, Integer>(i, j));
				if(cell.getEntity() != null && cell.getEntity().getEnergy() != null) {
					turnController.addEntity(cell.getEntity());
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
	
	public TurnController getTurnController() {
		return turnController;
	}
}
