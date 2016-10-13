package model;

import main.TinyRL;
import model.animations.rain.RainComponent;
import model.animations.rain.RainHandlerComponent;
import model.entities.EntityTileComponent;
import model.turns.TurnControllerAddEntityEvent;
import model.turns.TurnControllerClearEntitiesEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class WorldComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeCurrentRoomEvent) {
			ChangeCurrentRoomEvent changeCurrentRoomEvent = (ChangeCurrentRoomEvent)e;
			
			Engine.getInstance().removeEntitiesByComponentClass(RoomComponent.class);
			Engine.getInstance().removeEntitiesByComponentClass(RainHandlerComponent.class);
			Engine.getInstance().removeEntitiesByComponentClass(RainComponent.class);
			Engine.getInstance().removeEntitiesByComponentClass(EntityTileComponent.class);
			
			Room room = TinyRL.getInstance().getWorld().getRoom(changeCurrentRoomEvent.getNextRoom());
			
			Engine.getInstance().addEntity(room);
			Engine.getInstance().addEntities(room.getAnimationHandlers());
			Engine.getInstance().addEntities(room.getAnimations());
			Engine.getInstance().addEntities(room.getEntities());
			
			for(Entity entity : room.getEntitiesWithTurn()) {
				Engine.getInstance().addHeadEvent(new TurnControllerAddEntityEvent(entity));
			}
			Engine.getInstance().addHeadEvent(new TurnControllerClearEntitiesEvent());
		}
	}
}
