package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.TinyRL;
import model.animations.Animation;
import model.animations.AnimationHandler;
import model.entities.Entity;
import ui.Terminal;
import util.Pair;

public class Room {
	public final static int ROOM_SIZE = 9;

	private Pair<Integer, Integer> position;
	// [x][y]
	private Cell[][] cells;
	private List<AnimationHandler> animationHandlers = new ArrayList<>();
	
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
				if(!cell.getAnimations().isEmpty()) {
					Collections.sort(cell.getAnimations());
					Animation animation = cell.getAnimations().get(0);
					terminal.write(x, y, animation.getTile().tile, animation.getTile().color);
				}
				else if(cell.getEntity() != null) {
					terminal.write(x, y, cell.getEntity().getTile().tile, cell.getEntity().getTile().color);
				}
				else {
					terminal.write(x, y, cell.getGround().tile, cell.getGround().color);
				}
			}
		}
		terminal.repaint();
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
	
	public Pair<Integer, Integer> getPositionOfAnimation(Animation animation) {
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getAnimations().contains(animation)) {
					return new Pair<Integer, Integer>(x, y);
				}
			}
		}
		return null;
	}
	
	public Pair<Integer, Integer> getPosition() {
		return position;
	}
	
	public void setCell(Pair<Integer, Integer> position, Cell cell) {
		cells[position.key][position.value] = cell;
	}
	
	public Cell getCell(Pair<Integer, Integer> position) {
		return cells[position.key][position.value];
	}
	
	public List<AnimationHandler> getAnimationHandlers() {
		return animationHandlers;
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
