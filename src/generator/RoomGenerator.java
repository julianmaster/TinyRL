package generator;

import java.util.ArrayList;
import java.util.Random;

import model.Room;
import util.Pair;

public class RoomGenerator {
	
	private static Random rand = new Random();
	private static ArrayList<BaseRoom> rooms = new ArrayList<BaseRoom>() {{
		add(new StartingRoom());
	}};
	
	public static Room generateRoom(Pair<Integer, Integer> position) {
		Room room = new Room(position);
		int index = rand.nextInt(rooms.size());
		rooms.get(index).apply(room);
		return room;
	}
}
