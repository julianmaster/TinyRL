package model;
public class Room {
	public final static int ROOM_SIZE = 9;
	
	// [x][y]
	public Cell[][] cells;
	
	public Room() {
		cells = new Cell[ROOM_SIZE][ROOM_SIZE];
	}
	
	public void setCell(int x, int y, Cell cell) {
		cells[x][y] = cell;
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
}
