package model.entities.dead;

import java.util.List;

import model.Room;
import model.RoomComponent;
import model.entities.AttributesComponent;
import model.entities.KillEvent;
import model.items.Item;
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
			
			AttributesComponent attributesComponent = killEvent.getEntity().getComponentByClass(AttributesComponent.class);
			List<Item> items = attributesComponent.getAllItems();
			
			Engine.getInstance().addEntities(items);
			
			for(Item item : items) {
				room.getCell(positionEntity).getItems().add(item);
			}
			dropCorpse(room, positionEntity);
			
			Engine.getInstance().addHeadEvent(new TurnControllerRemoveEntityEvent(killEvent.getEntity()));
			Engine.getInstance().removeEntity(killEvent.getEntity());
		}
	}
	
	protected void dropCorpse(Room room, Pair<Integer, Integer> positionEntity) {
	}
}
