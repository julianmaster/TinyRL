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
	}};
	
	public static Room generateRoom(Pair<Integer, Integer> position) {
		Room room = EntityGenerator.newRoom(position);
		int index = rand.nextInt(rooms.size());
		rooms.get(index).apply(room);
		addArchitecture(room);
		return room;
	}
	
	public static void addArchitecture(Room room) {
		Entity extraEntity = rand.nextFloat() < 0.15 ? EntityGenerator.newRandomTree() : EntityGenerator.newWall();
		
		// North
		if(rand.nextFloat() < 0.1) {
			room.getCell(new Pair<Integer, Integer>(3, 1)).setEntity(extraEntity);
			room.getCell(new Pair<Integer, Integer>(5, 1)).setEntity(extraEntity);
		}
		
		// South
		if(rand.nextFloat() < 0.1) {
			room.getCell(new Pair<Integer, Integer>(3, 7)).setEntity(extraEntity);
			room.getCell(new Pair<Integer, Integer>(5, 7)).setEntity(extraEntity);
		}
		
		// West
		if(rand.nextFloat() < 0.1) {
			room.getCell(new Pair<Integer, Integer>(1, 3)).setEntity(extraEntity);
			room.getCell(new Pair<Integer, Integer>(1, 5)).setEntity(extraEntity);
		}
		
		// East
		if(rand.nextFloat() < 0.1) {
			room.getCell(new Pair<Integer, Integer>(7, 3)).setEntity(extraEntity);
			room.getCell(new Pair<Integer, Integer>(7, 5)).setEntity(extraEntity);
		}
		
		// Stop ?
		if(!rand.nextBoolean()) {
			return;
		}
		
		Entity extraEntity2 = rand.nextFloat() < 0.75 ? EntityGenerator.newRandomTree() : EntityGenerator.newWall();
		
		if(rand.nextFloat() < 0.25) {
			room.getCell(new Pair<Integer, Integer>(1, 1)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(1, 7)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(7, 7)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(7, 1)).setEntity(extraEntity2);
			return;
		}
		
		if(rand.nextFloat() < 0.25) {
			room.getCell(new Pair<Integer, Integer>(2, 1)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(1, 2)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(1, 6)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(2, 7)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(6, 7)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(7, 6)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(6, 1)).setEntity(extraEntity2);
			room.getCell(new Pair<Integer, Integer>(7, 2)).setEntity(extraEntity2);
			return;
		}
	}
}
