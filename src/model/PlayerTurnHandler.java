package model;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.entities.Door;
import model.entities.Entity;
import util.Pair;

public class PlayerTurnHandler implements TurnHandler {
	
	public Entity player;

	@Override
	public void init(Entity entity) {
		player = entity;
	}
	
	@Override
	public boolean turn() {
		KeyEvent event = TinyRL.terminal.getEvent();
		
		if(event == null) {
			return false;
		}
		else {
			TinyRL.terminal.setEvent(null);
		}
		
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
		
		return action;
	}

	public boolean moveActions(int dx, int dy) {
		Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		if(TinyRL.world.getCurrentRoom().getCell(position.key + dx, position.value + dy).getEntity() == null) {
			TinyRL.world.getCurrentRoom().setCell(position.key, position.value, null);
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
