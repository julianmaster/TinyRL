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
//		if(e instanceof TurnControllerEvent) {
//			processTurn();
//		}
//		else if(e instanceof TurnControllerAddEntityEvent) {
//			TurnControllerAddEntityEvent turnControllerAddEntityEvent = (TurnControllerAddEntityEvent)e;
//			entities.add(turnControllerAddEntityEvent.getEntity());
//		}
//		else if(e instanceof TurnControllerNextTurnEvent) {
//			processTurn();
//		}
		
		
		if(e instanceof TurnControllerEvent) {
			processTurn();
		}
		else if(e instanceof TurnControllerAddEntityEvent) {
			TurnControllerAddEntityEvent turnControllerAddEntityEvent = (TurnControllerAddEntityEvent)e;
			entities.add(turnControllerAddEntityEvent.getEntity());
		}
		else if(e instanceof NextTickTurnControllerEvent) {
			nextTickTurn();
			processTurn();
		}
		else if(e instanceof PerformTurnControllerEvent) {
			performEvent();
		}
	}
	
//	private void processTurn() {
//		do {
//			if(run) {
//				currentEntity = entities.get(position);
//				boolean perform = currentEntity.getEnergy().tick();
//				
//				if(perform) {
//					currentEntity.getEnergy().spend();
//					run = false;
//				}
//				
//				position++;
//				position = position % entities.size();
//			}
//			
//			if(!run) {
//				Engine.getInstance().addHeadEvent(new NextActionEvent(currentEntity, null));
//			}
//		} while(run);
//		
//		
//	}
	
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
