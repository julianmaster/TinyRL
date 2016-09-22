package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import model.entities.ModelEntities;
import model.entities.ModelEntity;
import model.turns.TurnControllerAddEntityEvent;
import model.turns.TurnControllerEntity;
import pattern.Engine;
import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	private Room currentRoom;
	
	public World() {
		world = new HashMap<>();
		Engine.getInstance().addEntity(TurnControllerEntity.newTurnController());
		init();
	}

	private void init() {
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(0, 0);
		createRoom(position);
		Room room = getRoom(position);
		
		Engine.getInstance().addEntities(room.getAnimationHandlers());
		Engine.getInstance().addEntities(room.getAnimations());
		Engine.getInstance().addEntities(room.getModelEntities());

		Pair<Integer, Integer> playerPosition = new Pair<Integer, Integer>(4, 4);
		ModelEntity player = ModelEntities.newPlayer(playerPosition);
		room.getCell(playerPosition).setEntity(player);
		Engine.getInstance().addEntity(player);
		Engine.getInstance().addHeadEvent(new TurnControllerAddEntityEvent(player));
		
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
		
		return true;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Room getRoom(Pair<Integer, Integer> position) {
		return world.get(position);
	}
}
