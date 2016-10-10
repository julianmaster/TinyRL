package model.turns.entities;

import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.turns.NextTickTurnControllerEvent;
import model.turns.TurnComponent;
import model.turns.actions.NextActionEvent;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class SkeletonTurnComponent extends TurnComponent {

	public SkeletonTurnComponent(int energyNeedToAction) {
		super(energyNeedToAction);
	}

	@Override
	public void process(Event e, double deltaTime) {
		super.process(e, deltaTime);

		if(e instanceof NextActionEvent) {
			Entity skeleton = Engine.getInstance().getEntityByComponent(this);
			PositionComponent positionComponent = skeleton.getComponentByClass(PositionComponent.class);
			
			Entity player = Engine.getInstance().getEntityByComponentClass(PlayerTurnComponent.class);
			PositionComponent playerPositionComponent = player.getComponentByClass(PositionComponent.class);
			
			Room currentRoom = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
			
			System.out.println(currentRoom.pathTo(positionComponent.getPosition(), playerPositionComponent.getPosition()));
			Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
		}
	}
}
