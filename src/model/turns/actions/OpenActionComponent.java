package model.turns.actions;

import model.Tile;
import model.entities.ChangeEntityTileEvent;
import model.entities.Door;
import model.entities.ModelEntity;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class OpenActionComponent implements Component {
	
	private boolean open = false;
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof OpenActionEvent) {
			open = true;
			
			ModelEntity modelEntity = (ModelEntity)Engine.getInstance().getEntityByComponent(this);
			if(modelEntity instanceof Door) {
				Engine.getInstance().addHeadEvent(new ChangeEntityTileEvent(modelEntity, Tile.OPEN_DOOR));
			}
		}
	}
	
	public boolean isOpen() {
		return open;
	}
}
