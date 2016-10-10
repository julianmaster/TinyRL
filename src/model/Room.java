package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.animations.Animation;
import model.entities.AttributesComponent;
import model.turns.TurnComponent;
import pattern.Entity;
import util.Pair;

public class Room extends Entity {
	public final static int ROOM_SIZE = 9;
	public final static List<Pair<Integer, Integer>> NEIGHBOURS_LOCATION_LIST = new LinkedList<Pair<Integer, Integer>>() {{
		add(new Pair<Integer, Integer>(-1, 0));
		add(new Pair<Integer, Integer>(0, -1));
		add(new Pair<Integer, Integer>(1, 0));
		add(new Pair<Integer, Integer>(0, 1));
		add(new Pair<Integer, Integer>(-1, -1));
		add(new Pair<Integer, Integer>(1, -1));
		add(new Pair<Integer, Integer>(1, 1));
		add(new Pair<Integer, Integer>(-1, 1));
	}};
	
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
	
	public List<Entity> getEntities() {
		List<Entity> entities = new ArrayList<>();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getEntity() != null) {
					entities.add(cells[x][y].getEntity());
				}
			}
		}
		return entities;
	}
	
	public List<Entity> getEntitiesWithTurn() {
		List<Entity> entities = new ArrayList<>();
		for(int x = 0; x < ROOM_SIZE; x++) {
			for(int y = 0; y < ROOM_SIZE; y++) {
				if(cells[x][y].getEntity() != null) {
					Entity entity = cells[x][y].getEntity();
					if(entity.getComponentByClass(TurnComponent.class) != null) {
						entities.add(cells[x][y].getEntity());
					}
				}
			}
		}
		return entities;
	}
	
	public ArrayList<Pair<Integer, Integer>> pathTo(Pair<Integer, Integer> start, Pair<Integer, Integer> goal) {
		int[][] ranges = new int[ROOM_SIZE][ROOM_SIZE];
		for(int i = 0; i < ROOM_SIZE; i++) {
			for(int j = 0; j < ROOM_SIZE; j++) {
				ranges[i][j] = -1;
			}
		}
		ArrayDeque<Pair<Integer, Integer>> cellToProcess = new ArrayDeque<>();
		cellToProcess.add(start);
		ranges[start.key][start.value] = 0;
		
		while(!cellToProcess.isEmpty()) {
			Pair<Integer, Integer> cell = cellToProcess.pollFirst();
			
			ArrayList<Pair<Integer, Integer>> neighbours = getNeighbours(cell, NEIGHBOURS_LOCATION_LIST);
			for(Pair<Integer, Integer> neighbour : neighbours) {
				if(neighbour.key == goal.key && neighbour.value == goal.value) {
					ranges[neighbour.key][neighbour.value] = ranges[cell.key][cell.value] + 1;
					return buildPath(ranges, neighbour);
				}
				
				if(ranges[neighbour.key][neighbour.value] == -1 || ranges[neighbour.key][neighbour.value] > ranges[cell.key][cell.value] + 1) {
					cellToProcess.add(neighbour);
					ranges[neighbour.key][neighbour.value] = ranges[cell.key][cell.value] + 1;
				}
			}
		}
		
		return null;
	}
	
	private ArrayList<Pair<Integer, Integer>> getNeighbours(Pair<Integer, Integer> position, List<Pair<Integer, Integer>> neighboursLocation) {
		ArrayList<Pair<Integer, Integer>> neighbours = new ArrayList<>();
		
		for(Pair<Integer, Integer> neighbourLocation: neighboursLocation) {
			int i = position.key + neighbourLocation.key;
			int j = position.value + neighbourLocation.value;
			
			Entity entity = cells[i][j].getEntity();
			// FIXME : Change from all AttributesComponent Entity to only the Entity position you need to go (Other Entities isn't passable)
			if(i >= 0 && i < ROOM_SIZE && j >= 0 && j < ROOM_SIZE && (entity == null || entity.getComponentByClass(AttributesComponent.class) != null)) {
				neighbours.add(new Pair<Integer, Integer>(i, j));
			}
		}
		
		return neighbours;
	}
	
	private ArrayList<Pair<Integer, Integer>> buildPath(int[][] ranges, Pair<Integer, Integer> position) {
		if(ranges[position.key][position.value] == 0) {
			ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
			path.add(position);
			return path;
		}
		else {
			Pair<Integer, Integer> mostNear = position;
			for(Pair<Integer, Integer> neighbour : getNeighbours(position, NEIGHBOURS_LOCATION_LIST)) {
				if(ranges[neighbour.key][neighbour.value] < ranges[mostNear.key][mostNear.value] && ranges[neighbour.key][neighbour.value] != -1) {
					mostNear = neighbour;
				}
			}
			
			ArrayList<Pair<Integer, Integer>> path = buildPath(ranges, mostNear);
			path.add(position);
			return path;
		}
	}
}
