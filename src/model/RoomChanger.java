package model;

import util.Pair;

public interface RoomChanger {
	public Pair<Integer, Integer> changeRoom();
	public Direction getDirection();
}
