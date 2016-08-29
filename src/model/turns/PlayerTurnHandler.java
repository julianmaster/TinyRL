package model.turns;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.Openable;
import model.Room;
import model.RoomChanger;
import model.entities.Door;
import model.entities.Entity;
import ui.CustomAsciiTerminal;
import util.Pair;

public class PlayerTurnHandler implements TurnHandler {
	
	public Entity player;

	@Override
	public void init(Entity entity) {
		player = entity;
	}
	
	@Override
	public boolean turn() {
		CustomAsciiTerminal asciiTerminal = TinyRL.getInstance().getAsciiTerminal();
		KeyEvent event = asciiTerminal.getEvent();
		
		if(event != null) {
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
			
			asciiTerminal.setEvent(null);
			
			return action;
		}
		else {
			return false;
		}
	}

	public boolean moveActions(int dx, int dy) {
		Pair<Integer, Integer> positionPlayer = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		Pair<Integer, Integer> positionTarget = positionPlayer.clone();
		positionTarget.key += dx;
		positionTarget.value += dy;
		
		if(TinyRL.world.getCurrentRoom().getCell(positionTarget).getEntity() == null) {
			TinyRL.world.getCurrentRoom().getCell(positionPlayer).setEntity(null);
			TinyRL.world.getCurrentRoom().getCell(positionTarget).setEntity(player);
			return true;
		}
		return open(dx, dy);
	}
	
	public boolean open(int dx, int dy) {
		Pair<Integer, Integer> positionTarget = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		positionTarget.key += dx;
		positionTarget.value += dy;
		Entity entity = TinyRL.world.getCurrentRoom().getCell(positionTarget).getEntity();
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
		Pair<Integer, Integer> positionPlayer = TinyRL.world.getCurrentRoom().getPositionOfEntity(player);
		Pair<Integer, Integer> positionTarget = positionPlayer.clone();
		positionTarget.key += dx;
		positionTarget.value += dy;
		
		Entity entity = TinyRL.world.getCurrentRoom().getCell(positionTarget).getEntity();
		if(entity instanceof RoomChanger) {
			RoomChanger roomChanger = (RoomChanger)entity;
			Pair<Integer, Integer> nextRoom = roomChanger.changeRoom();
			// Remove player of the current room
			TinyRL.world.getCurrentRoom().getCell(positionPlayer).setEntity(null);
			
			TinyRL.world.createRoom(nextRoom);
			
			Room room = TinyRL.world.getRoom(nextRoom);
			Door door = null;
			switch (roomChanger.getDirection()) {
				case N:
					room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 2)).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1)).getEntity();
					break;
					
				case S:
					room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 1)).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0)).getEntity();
					break;
					
				case E:
					room.getCell(new Pair<Integer, Integer>(1, (Room.ROOM_SIZE - 1) / 2)).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2)).getEntity();
					break;
					
				case W:
					room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 2, (Room.ROOM_SIZE - 1) / 2)).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2)).getEntity();
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
