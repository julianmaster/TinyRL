package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Entity;

public class TurnController {
	private List<Entity> entities = new ArrayList<>();
	private int position = 0;
	
	public boolean addEntity(Entity entity) {
		return entities.add(entity);
	}
	
	public void removeAllEntities() {
		entities.clear();
	}
	
	public void process() {
		boolean run = true;
		while(run) {
			Entity current = entities.get(position);
			boolean perform = current.getEnergy().tick();
			if(perform) {
				current.getEnergy().spend();
				run = current.getInput().handleInput(current);
			}
			position++;
			position = position % entities.size();
		}
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
