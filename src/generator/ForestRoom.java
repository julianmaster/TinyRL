package generator;

import java.util.ArrayList;
import java.util.Random;

import model.Ground;
import model.Room;
import model.entities.EntityTile;
import pattern.Entity;
import util.Pair;

public class ForestRoom extends BaseRoom {
	
	Random rand = new Random();
	private static ArrayList<Ground> grounds = new ArrayList<Ground>() {{
		add(Ground.GRASS1);
		add(Ground.GRASS2);
		add(Ground.HIGH_GRASS1);
		add(Ground.HIGH_GRASS2);
	}};
	
	private static ArrayList<EntityTile> trees = new ArrayList<EntityTile>() {{
		add(EntityTile.TREE1);
		add(EntityTile.TREE2);
		add(EntityTile.TREE3);
	}};

	@Override
	public void apply(Room room) {
		super.initRoom(room, Ground.DIRT);
		super.placeWall(room);
		super.placeDoor(room);
		
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Pair<Integer, Integer> position = new Pair<Integer, Integer>(x, y);
				Ground ground = grounds.get(rand.nextInt(grounds.size()));
				room.getCell(position).setGround(ground);
				
				if(x > 1 && x < Room.ROOM_SIZE - 2 && y > 1 && y < Room.ROOM_SIZE - 2) {
					if(rand.nextBoolean()) {
						EntityTile treeTile = trees.get(rand.nextInt(trees.size()));
						Entity tree = EntityGenerator.newTree(treeTile);
						room.getCell(position).setEntity(tree);
					}
				}
			}
		}
	}
}
