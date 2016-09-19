package model.entities;

import model.Direction;
import model.Openable;
import model.RoomChanger;
import model.Tile;
import util.Pair;

public class Door extends ModelEntity implements Openable, RoomChanger {
	private boolean open = false;
	private Pair<Integer, Integer> nextRoom;
	private Direction direction;
	
	public Door(Pair<Integer, Integer> nextRoom, Direction direction) {
		super(Tile.CLOSE_DOOR, null);
		this.nextRoom = nextRoom;
		this.direction = direction;
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

	@Override
	public Pair<Integer, Integer> changeRoom() {
		return nextRoom;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}
}
