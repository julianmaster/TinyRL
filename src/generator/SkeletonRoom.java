package generator;

import java.util.Random;

import model.Ground;
import model.Room;
import util.Pair;

public class SkeletonRoom extends BaseRoom {
	
	public static final int MIN_SKELETON = 2;
	public static final int MAX_SKELETON = 6;
	private Random rand = new Random();

	@Override
	public void apply(Room room) {
		super.initRoom(room, Ground.STONE);
		super.placeWall(room);
		super.placeDoor(room);
		
		int skeletons = rand.nextInt(MAX_SKELETON - MIN_SKELETON) + MIN_SKELETON;
		for(int i = 0; i < skeletons; i++) {
			int x = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
			int y = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
			
			Pair<Integer, Integer> position = new Pair<Integer, Integer>(x, y);
			room.getCell(position).setEntity(EntityGenerator.newSkeleton(position));
		}
	}
}
