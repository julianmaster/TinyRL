package model;

import java.util.HashMap;
import java.util.Map;

import generator.EntityGenerator;
import generator.RoomGenerator;
import model.entities.ModelEntity;
import model.turns.TurnControllerAddEntityEvent;
import pattern.Engine;
import pattern.Entity;
import util.Pair;

public class World extends Entity {
	private Map<Pair<Integer, Integer>, Room> world;
	
	public World() {
		world = new HashMap<>();
		Engine.getInstance().addEntity(EntityGenerator.newTurnController());
		init();
	}

	private void init() {
		Pair<Integer, Integer> position = new Pair<Integer, Integer>(0, 0);
		createRoom(position);
		Room room = getRoom(position);
		
		Engine.getInstance().addEntity(room);
		Engine.getInstance().addEntities(room.getAnimationHandlers());
		Engine.getInstance().addEntities(room.getAnimations());
		Engine.getInstance().addEntities(room.getModelEntities());

		Pair<Integer, Integer> playerPosition = new Pair<Integer, Integer>(4, 4);
		ModelEntity player = EntityGenerator.newPlayer(playerPosition);
		room.getCell(playerPosition).setEntity(player);
		Engine.getInstance().addEntity(player);
		Engine.getInstance().addHeadEvent(new TurnControllerAddEntityEvent(player));
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
	
	public Room getRoom(Pair<Integer, Integer> position) {
		return world.get(position);
	}
}
