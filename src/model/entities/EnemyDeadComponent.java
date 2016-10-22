package model.entities;

import model.Room;
import model.RoomComponent;
import model.turns.TurnControllerRemoveEntityEvent;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class EnemyDeadComponent extends DeadComponent {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof KillEvent) {
			KillEvent killEvent = (KillEvent)e;
			System.out.println("EnemyDeadComponent: enemy is dead");
			Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
			Pair<Integer, Integer> positionEntity = room.getPositionOfEntity(killEvent.getEntity());
			room.getCell(positionEntity).setEntity(null);
			
			Engine.getInstance().addHeadEvent(new TurnControllerRemoveEntityEvent(killEvent.getEntity()));
			Engine.getInstance().removeEntity(killEvent.getEntity());
		}
	}
}
