package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import main.TinyRL;
import model.animations.AnimationController;
import model.animations.AnimationHandler;
import model.entities.Player;
import model.turns.TurnController;
import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	private Room currentRoom;
	private TurnController turnController;
	private AnimationController animationController;
	
	public World() {
		world = new HashMap<>();
		turnController = new TurnController();
		animationController = new AnimationController();
		init();
	}

	private void init() {
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(0, 0);
		createRoom(position);
		Room room = getRoom(position);
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
	
	public AnimationController getAnimationController() {
		return animationController;
	}
	
	public TurnController getTurnController() {
		return turnController;
	}
}
