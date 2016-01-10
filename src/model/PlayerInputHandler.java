package model;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.entities.Door;
import model.entities.Entity;
import util.Observable;
import util.Observer;
import util.Pair;

public class PlayerInputHandler implements InputHandler, Observer {
	
	public Entity player;

	@Override
	public boolean handleInput(Entity entity) {
		player = entity;
		TinyRL.terminal.addKeyPressedObserver(this);
		return false;
	}

	@Override
	public void updateObserver(Observable observable) {
		KeyEvent event = TinyRL.terminal.getEvent();
		
		boolean action = false;
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			action = moveActions(0, -1);
		}
		if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			action = moveActions(0, 1);
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			action = moveActions(-1, 0);
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			action = moveActions(1, 0);
		}
		
		if(action) {
			TinyRL.terminal.deleteKeyPressedObservers();
			TinyRL.world.run();
		}
	}
	
	public boolean moveActions(int dx, int dy) {
		Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		if(TinyRL.world.getCurrentRoom().getCell(position.key + dx, position.value + dy).getEntity() == null) {
			TinyRL.world.getCurrentRoom().getCellOfEntity(player).setEntity(null);
			TinyRL.world.getCurrentRoom().getCell(position.key + dx, position.value + dy).setEntity(player);
			return true;
		}
		return open(dx, dy);
	}
	
	public boolean open(int dx, int dy) {
		Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		Entity entity = TinyRL.world.getCurrentRoom().getCell(position.key + dx, position.value + dy).getEntity();
		if(entity instanceof Openable) {
			Openable openable = (Openable)entity;
			if(!openable.isOpen()) {
				openable.open();
				return true;
			}
			return changeRoom(dx, dy);
		}
		return false;
	}
	
	public boolean changeRoom(int dx, int dy) {
		Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		Entity entity = TinyRL.world.getCurrentRoom().getCell(position.key + dx, position.value + dy).getEntity();
		if(entity instanceof RoomChanger) {
			RoomChanger roomChanger = (RoomChanger)entity;
			Pair<Integer, Integer> nextRoom = roomChanger.changeRoom();
			// Remove player of the current room
			TinyRL.world.getCurrentRoom().getCell(position.key, position.value).setEntity(null);
			
			TinyRL.world.createRoom(nextRoom);
			
			Room room = TinyRL.world.getRoom(nextRoom);
			Door door = null;
			switch (roomChanger.getDirection()) {
				case N:
					room.getCell((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 2).setEntity(player);
					door = (Door)room.getCell((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1).getEntity();
					break;
					
				case S:
					room.getCell((Room.ROOM_SIZE - 1) / 2, 1).setEntity(player);
					door = (Door)room.getCell((Room.ROOM_SIZE - 1) / 2, 0).getEntity();
					break;
					
				case E:
					room.getCell(1, (Room.ROOM_SIZE - 1) / 2).setEntity(player);
					door = (Door)room.getCell(0, (Room.ROOM_SIZE - 1) / 2).getEntity();
					break;
					
				case W:
					room.getCell(Room.ROOM_SIZE - 2, (Room.ROOM_SIZE - 1) / 2).setEntity(player);
					door = (Door)room.getCell(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2).getEntity();
					break;
				
				case NE:
					break;
				
				case NW:
					break;
				
				case SE:
					break;
				
				case SW:
					break;
				
				default:
					break;
			}
			door.open();
			
			TinyRL.world.loadRoom(nextRoom);
			return true;
		}
		return false;
	}
}
