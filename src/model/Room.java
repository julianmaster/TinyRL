package model;

import java.awt.Point;

import main.TinyRL;
import ui.Terminal;
import util.Pair;

public class Room {
	public final static int ROOM_SIZE = 9;

	private Pair<Integer, Integer> position;
	// [x][y]
	private Cell[][] cells;
	
	public Room(Pair<Integer, Integer> position) {
		this.cells = new Cell[ROOM_SIZE][ROOM_SIZE];
		this.position = position;
	}
	
	public void draw() {
		Terminal terminal = TinyRL.terminal;
		terminal.clear();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				Cell cell = cells[x][y];
				if(cell.getEntity() != null) {
					terminal.write(x, y, cell.getEntity().getTile().tile, cell.getEntity().getTile().color);
				}
				else {
					terminal.write(x, y, cell.getGround().tile, cell.getGround().color);
				}
			}
		}
		terminal.repaint();
	}
	
	public void setCell(int x, int y, Cell cell) {
		cells[x][y] = cell;
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public Cell getCellOfEntity(Entity entity) {
		Pair<Integer, Integer> position = getPositionOfEntity(entity);
		if(position != null) {
			return cells[position.key][position.value];
		}
		
		return null;
	}
	
	public Pair<Integer, Integer> getPositionOfEntity(Entity entity) {
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getEntity() != null && cells[x][y].getEntity().equals(entity)) {
					return new Pair<Integer, Integer>(x, y);
				}
			}
		}
		return null;
	}

	public Pair<Integer, Integer> getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		String s = new String();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				Cell cell = cells[x][y];
				if(cell.getEntity() != null) {
					s += cell.getEntity().getTile().tile;
				}
				else {
					s += cell.getGround().tile;
				}
			}
			s += '\n';
		}
		return s;
	}
}
