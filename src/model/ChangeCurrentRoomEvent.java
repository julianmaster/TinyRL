package model;

import pattern.ComponentEvent;
import util.Pair;

public class ChangeCurrentRoomEvent extends ComponentEvent {
	
	private Pair<Integer, Integer> nextRoom;

	public ChangeCurrentRoomEvent(Pair<Integer, Integer> nextRoom) {
		super(WorldComponent.class);
		this.nextRoom = nextRoom;
	}
	
	public Pair<Integer, Integer> getNextRoom() {
		return nextRoom;
	}
}
