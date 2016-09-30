package model;

import java.util.ArrayList;
import java.util.List;

import model.animations.Animation;
import pattern.Entity;
import util.Pair;

public class Room extends Entity {
	public final static int ROOM_SIZE = 9;

	private Pair<Integer, Integer> position;
	// [x][y]
	private Cell[][] cells;
	private List<Entity> animationHandlers = new ArrayList<>();
	
	public Room(Pair<Integer, Integer> position) {
		this.cells = new Cell[ROOM_SIZE][ROOM_SIZE];
		this.position = position;
	}
	
	public <E extends Entity> List<Pair<Integer, Integer>> getPositionOfEntityType(Class<E> classEntity) {
		List<Pair<Integer, Integer>> result = new ArrayList<>();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getEntity() != null && cells[x][y].getEntity().getClass() == classEntity) {
					result.add(new Pair<Integer, Integer>(x, y));
				}
			}
		}
		return result;
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
	
	public List<Entity> getAnimationHandlers() {
		return animationHandlers;
	}
	
	public List<Entity> getAnimations() {
		List<Entity> animations = new ArrayList<>();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(!cells[x][y].getAnimations().isEmpty()) {
					animations.addAll(cells[x][y].getAnimations());
				}
			}
		}
		return animations;
	}
	
	public List<Entity> getModelEntities() {
		List<Entity> modelEntities = new ArrayList<>();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getEntity() != null) {
					modelEntities.add(cells[x][y].getEntity());
				}
			}
		}
		return modelEntities;
	}
}
