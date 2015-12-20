package model;

import java.util.HashMap;
import java.util.Map;

import util.Pair;

public class World {
	private Map<Pair<Integer, Integer>, Room> world;
	
	public World() {
		world = new HashMap<>();
	}

	public World(Map<Pair<Integer, Integer>, Room> world) {
		this.world = world;
	}
}
