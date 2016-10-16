package model.turns.actions;

import model.ChangePositionEvent;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.turns.NextTickTurnControllerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class MoveActionComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof MoveActionEvent) {
			MoveActionEvent moveActionEvent = (MoveActionEvent)e;

			Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
			
			Entity entity = moveActionEvent.getEntity();
			PositionComponent positionComponent = entity.getComponentByClass(PositionComponent.class);
			
			Pair<Integer, Integer> positionPlayer = positionComponent.getPosition();
			Pair<Integer, Integer> positionTarget = positionPlayer.clone();
			
			positionTarget.key += moveActionEvent.getDx();
			positionTarget.value += moveActionEvent.getDy();
			
			if(room.getCell(positionTarget).getEntity() == null) {
				room.getCell(positionPlayer).setEntity(null);
				room.getCell(positionTarget).setEntity(entity);
				
				Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
				Engine.getInstance().addHeadEvent(new ChangePositionEvent(entity, positionTarget));
			}
			else {
				Engine.getInstance().addHeadEvent(new NextActionEvent(entity, moveActionEvent));
			}
		}
	}
}
