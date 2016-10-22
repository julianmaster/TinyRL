package model.turns;

import java.util.ArrayList;
import java.util.List;

import model.turns.actions.NextActionEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class TurnControllerComponent implements Component {
	private List<Entity> entities = new ArrayList<>();
	private int position = 0;
	private boolean run = true;
	private Entity currentEntity = null;

	@Override
	public void process(Event e, double deltaTime) {
		// Turn
		if(e instanceof TurnControllerEvent) {
			processTurn();
		}
		else if(e instanceof NextTickTurnControllerEvent) {
			nextTickTurn();
			processTurn();
		}
		else if(e instanceof PerformTurnControllerEvent) {
			performEvent();
		}
		
		// Management
		else if(e instanceof TurnControllerAddEntityEvent) {
			TurnControllerAddEntityEvent turnControllerAddEntityEvent = (TurnControllerAddEntityEvent)e;
			if(turnControllerAddEntityEvent.getEntity().getComponentByClass(TurnComponent.class) != null) {
				entities.add(turnControllerAddEntityEvent.getEntity());
			}
		}
		else if(e instanceof TurnControllerClearEntitiesEvent) {
			position = 0;
			entities.clear();
		}
		else if(e instanceof TurnControllerRemoveEntityEvent) {
			TurnControllerRemoveEntityEvent turnControllerRemoveEntityEvent = (TurnControllerRemoveEntityEvent)e;
			entities.remove(turnControllerRemoveEntityEvent.getEntityToRemove());
		}
	}
	
	private void nextTickTurn() {
		run = true;
		position++;
		position = position % entities.size();
	}
	
	private void processTurn() {
		if(!run) {
			Engine.getInstance().addHeadEvent(new NextActionEvent(currentEntity, null));
		}
		else {
			currentEntity = entities.get(position);
			Engine.getInstance().addHeadEvent(new TickTurnEvent(currentEntity));
		}
	}
	
	private void performEvent() {
		run = false;
		Engine.getInstance().addHeadEvent(new NextActionEvent(currentEntity, null));
	}
}
