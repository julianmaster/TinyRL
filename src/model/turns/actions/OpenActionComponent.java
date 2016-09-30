package model.turns.actions;

import model.Tile;
import model.entities.ChangeEntityTileEvent;
import model.entities.DoorComponent;
import model.turns.NextTickTurnControllerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class OpenActionComponent implements Component {
	
	private boolean open = false;
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof OpenActionEvent) {
			open = true;
			Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
			
			Entity entity = Engine.getInstance().getEntityByComponent(this);
			if(entity.getComponentByClass(DoorComponent.class) != null) {
				Engine.getInstance().addHeadEvent(new ChangeEntityTileEvent(entity, Tile.OPEN_DOOR));
			}
		}
	}
	
	public boolean isOpen() {
		return open;
	}
}
