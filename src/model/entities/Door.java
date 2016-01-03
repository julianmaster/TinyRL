package model.entities;

import util.Pair;
import model.Entity;
import model.Openable;
import model.RoomChanger;
import model.Tile;

public class Door extends Entity implements Openable, RoomChanger {
	private boolean open = false;
	public Pair<Integer, Integer> nextRoom;
	
	public Door(Pair<Integer, Integer> nextRoom) {
		super(Tile.CLOSE_DOOR, null, null);
		this.nextRoom = nextRoom;
	}

	@Override
	public void open() {
		open = true;
		super.setTile(Tile.OPEN_DOOR);
	}

	@Override
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public Pair<Integer, Integer> changeRoom() {
		return nextRoom;
	}
}
