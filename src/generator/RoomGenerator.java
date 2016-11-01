package generator;

import java.util.ArrayList;
import java.util.Random;

import model.Room;
import pattern.Entity;
import util.Pair;

public class RoomGenerator {
	
	private static Random rand = new Random();
	private static ArrayList<BaseRoom> rooms = new ArrayList<BaseRoom>() {{
		add(new EmptyRoom());
		add(new LandRoom());
		add(new ForestRoom());
		add(new SkeletonRoom());
	}};
	
	public static Room generateRoom(Pair<Integer, Integer> position) {
		Room room = EntityGenerator.newRoom(position);
		int index = rand.nextInt(rooms.size());
		rooms.get(index).apply(room);
		addExtraArchitecture(room);
		return room;
	}
	
	public static void addExtraArchitecture(Room room) {
		Entity entity1 = null;
		Entity entity2 = null;
		Entity entity3 = null;
		Entity entity4 = null;
		Entity entity5 = null;
		Entity entity6 = null;
		Entity entity7 = null;
		Entity entity8 = null;
		
		boolean isTree = rand.nextFloat() < 0.15;
		
		// North
		if(rand.nextFloat() < 0.1) {
			if(isTree) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(3, 1)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(5, 1)).setEntity(entity2);
		}
		
		// South
		if(rand.nextFloat() < 0.1) {
			if(isTree) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(3, 7)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(5, 7)).setEntity(entity2);
		}
		
		// West
		if(rand.nextFloat() < 0.1) {
			if(isTree) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(1, 3)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(1, 5)).setEntity(entity2);
		}
		
		// East
		if(rand.nextFloat() < 0.1) {
			if(isTree) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(7, 3)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(7, 5)).setEntity(entity2);
		}
		
		// Stop ?
		if(!rand.nextBoolean()) {
			return;
		}
		
		boolean isTree2 = rand.nextFloat() < 0.75;
		
		if(rand.nextFloat() < 0.25) {
			if(isTree2) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
				entity3 = EntityGenerator.newRandomTree();
				entity4 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
				entity3 = EntityGenerator.newWall();
				entity4 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(1, 1)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(1, 7)).setEntity(entity2);
			room.getCell(new Pair<Integer, Integer>(7, 7)).setEntity(entity3);
			room.getCell(new Pair<Integer, Integer>(7, 1)).setEntity(entity4);
			return;
		}
		
		if(rand.nextFloat() < 0.25) {
			if(isTree2) {
				entity1 = EntityGenerator.newRandomTree();
				entity2 = EntityGenerator.newRandomTree();
				entity3 = EntityGenerator.newRandomTree();
				entity4 = EntityGenerator.newRandomTree();
				entity5 = EntityGenerator.newRandomTree();
				entity6 = EntityGenerator.newRandomTree();
				entity7 = EntityGenerator.newRandomTree();
				entity8 = EntityGenerator.newRandomTree();
			}
			else {
				entity1 = EntityGenerator.newWall();
				entity2 = EntityGenerator.newWall();
				entity3 = EntityGenerator.newWall();
				entity4 = EntityGenerator.newWall();
				entity5 = EntityGenerator.newWall();
				entity6 = EntityGenerator.newWall();
				entity7 = EntityGenerator.newWall();
				entity8 = EntityGenerator.newWall();
			}
			room.getCell(new Pair<Integer, Integer>(2, 1)).setEntity(entity1);
			room.getCell(new Pair<Integer, Integer>(1, 2)).setEntity(entity2);
			room.getCell(new Pair<Integer, Integer>(1, 6)).setEntity(entity3);
			room.getCell(new Pair<Integer, Integer>(2, 7)).setEntity(entity4);
			room.getCell(new Pair<Integer, Integer>(6, 7)).setEntity(entity5);
			room.getCell(new Pair<Integer, Integer>(7, 6)).setEntity(entity6);
			room.getCell(new Pair<Integer, Integer>(6, 1)).setEntity(entity7);
			room.getCell(new Pair<Integer, Integer>(7, 2)).setEntity(entity8);
			return;
		}
		
		switch (rand.nextInt(4)) {
			case 0:
				Entity entity = isTree2 ? EntityGenerator.newRandomTree() : EntityGenerator.newWall();
				room.getCell(new Pair<Integer, Integer>(4, 4)).setEntity(entity);
			break;
			
			case 1:
				if(isTree2) {
					entity1 = EntityGenerator.newRandomTree();
					entity2 = EntityGenerator.newRandomTree();
					entity3 = EntityGenerator.newRandomTree();
					entity4 = EntityGenerator.newRandomTree();
				}
				else {
					entity1 = EntityGenerator.newWall();
					entity2 = EntityGenerator.newWall();
					entity3 = EntityGenerator.newWall();
					entity4 = EntityGenerator.newWall();
				}
				room.getCell(new Pair<Integer, Integer>(3, 3)).setEntity(entity1);
				room.getCell(new Pair<Integer, Integer>(3, 5)).setEntity(entity2);
				room.getCell(new Pair<Integer, Integer>(5, 5)).setEntity(entity3);
				room.getCell(new Pair<Integer, Integer>(5, 3)).setEntity(entity4);
			break;
			
			case 2:
				if(isTree2) {
					entity1 = EntityGenerator.newRandomTree();
					entity2 = EntityGenerator.newRandomTree();
					entity3 = EntityGenerator.newRandomTree();
					entity4 = EntityGenerator.newRandomTree();
				}
				else {
					entity1 = EntityGenerator.newWall();
					entity2 = EntityGenerator.newWall();
					entity3 = EntityGenerator.newWall();
					entity4 = EntityGenerator.newWall();
				}
				room.getCell(new Pair<Integer, Integer>(2, 4)).setEntity(entity1);
				room.getCell(new Pair<Integer, Integer>(6, 4)).setEntity(entity2);
				room.getCell(new Pair<Integer, Integer>(4, 6)).setEntity(entity3);
				room.getCell(new Pair<Integer, Integer>(4, 2)).setEntity(entity4);
			break;
				
			case 3:
				if(isTree2) {
					entity1 = EntityGenerator.newRandomTree();
					entity2 = EntityGenerator.newRandomTree();
					entity3 = EntityGenerator.newRandomTree();
					entity4 = EntityGenerator.newRandomTree();
				}
				else {
					entity1 = EntityGenerator.newWall();
					entity2 = EntityGenerator.newWall();
					entity3 = EntityGenerator.newWall();
					entity4 = EntityGenerator.newWall();
				}
				room.getCell(new Pair<Integer, Integer>(2, 2)).setEntity(entity1);
				room.getCell(new Pair<Integer, Integer>(6, 2)).setEntity(entity2);
				room.getCell(new Pair<Integer, Integer>(6, 6)).setEntity(entity3);
				room.getCell(new Pair<Integer, Integer>(2, 6)).setEntity(entity4);
			
				if(rand.nextFloat() < 0.25) {
					if(isTree2) {
						entity1 = EntityGenerator.newRandomTree();
						entity2 = EntityGenerator.newRandomTree();
						entity3 = EntityGenerator.newRandomTree();
						entity4 = EntityGenerator.newRandomTree();
					}
					else {
						entity1 = EntityGenerator.newWall();
						entity2 = EntityGenerator.newWall();
						entity3 = EntityGenerator.newWall();
						entity4 = EntityGenerator.newWall();
					}
					room.getCell(new Pair<Integer, Integer>(2, 4)).setEntity(entity1);
					room.getCell(new Pair<Integer, Integer>(6, 4)).setEntity(entity2);
					room.getCell(new Pair<Integer, Integer>(4, 6)).setEntity(entity3);
					room.getCell(new Pair<Integer, Integer>(4, 2)).setEntity(entity4);
				}
			break;
		}
	}
}
