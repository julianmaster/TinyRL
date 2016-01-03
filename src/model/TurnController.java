package model;

import java.util.ArrayList;
import java.util.List;

public class TurnController {
	public List<Entity> entities = new ArrayList<>();
	public int position = 0;
	
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
				run = current.getInput().handleInput(current);
			}
			position++;
			position = position % entities.size();
		}
	}
}
