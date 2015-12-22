package generator;

import model.Cell;
import model.Ground;
import model.Room;

public class EmptyRoom extends BaseRoom {

	@Override
	public void apply(Room room) {
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Cell cell = new Cell(null, null, Ground.DIRT);
				
				if(x = 0 && y = 0) {
					// Ajouter le joueur
					cell.setEntity(entity);
				}
				
				room.setCell(x, y, cell);
			}
		}
	}
}
