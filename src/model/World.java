package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import generator.EntityGenerator;
import generator.RoomGenerator;
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
		
		Pair<Integer, Integer> playerPosition = new Pair<Integer, Integer>(4, 4);
		Entity player = EntityGenerator.newPlayer(playerPosition);
		room.getCell(playerPosition).setEntity(player);
		
		Engine.getInstance().addEntity(room);
		Engine.getInstance().addEntities(room.getAnimationHandlers());
		Engine.getInstance().addEntities(room.getAnimations());
		List<Entity> entities = room.getEntities();
		Engine.getInstance().addEntities(entities);
		for(Entity entity : room.getEntitiesWithTurn()) {
			Engine.getInstance().addNextTurnEvent(new TurnControllerAddEntityEvent(entity));
		}
		
		System.out.println(entities.size());
//		Engine.getInstance().addEntity(player);
//		Engine.getInstance().addHeadEvent(new TurnControllerAddEntityEvent(player));
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
