package model;

import generator.RoomGenerator;

import java.util.HashMap;
import java.util.Map;

import main.TinyRL;
import model.animations.AnimationController;
import model.animations.Rain;
import model.entities.Player;
import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	private Room currentRoom;
	private TurnController turnController;
	private AnimationController animationController;
//	private WindowCloseHandler windowCloseHandler;
	
	public World() {
		world = new HashMap<>();
		turnController = new TurnController();
		animationController = new AnimationController();
//		windowCloseHandler = new WindowCloseHandler();
//		TinyRL.terminal.getWindow().addWindowListener(windowCloseHandler);
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
		long lastLoopTime = System.nanoTime();
		
		animationController.addAnimation(new Rain(new Pair<Integer, Integer>(0, 0), 5));
		
		while(true) {
			long now = System.nanoTime();
			double updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / TinyRL.OPTIMAL_TIME;
			
			boolean changed = false;
			
			if(!animationController.done()) {
				changed = animationController.update(delta);
				if(changed) {
					animationController.
				}
			}
			else {
				changed = turnController.update();
			}
			
			if(changed) {
				currentRoom.draw();
			}
			
			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + TinyRL.OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
			}
		}
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
				Cell cell = currentRoom.getCell(i, j);
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
}
