package model.turns;

import java.util.ArrayList;
import java.util.List;

import model.entities.Entity;

public class TurnController {
	private List<Entity> entities = new ArrayList<>();
	private int position = 0;
	
	private boolean run = true;
	private Entity currentEntity = null;
	
	public boolean addEntity(Entity entity) {
		return entities.add(entity);
	}
	
	public void removeAllEntities() {
		entities.clear();
	}
	
	public boolean update() {
		boolean changed = false;
		do {
			if(run) {
				currentEntity = entities.get(position);
				boolean perform = currentEntity.getEnergy().tick();
				
				if(perform) {
					currentEntity.getEnergy().spend();
					currentEntity.getTurnHandler().init(currentEntity);
					run = false;
					changed = true;
				}
				
				position++;
				position = position % entities.size();
			}
			
			if(!run) {
				run = currentEntity.getTurnHandler().turn();
			}
		} while(run);
		
		return changed;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
