package model.animations.attack;

import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.animations.Animation;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class AttackAnimationComponent implements Component {

	private final static int FPS = 12;
	private double elapseTime = 0;
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof TickAttackAnimationEvent) {
			elapseTime += deltaTime;
			if(elapseTime >= 60 / FPS) {
				Animation attackAnimation = (Animation)Engine.getInstance().getEntityByComponent(this);
				PositionComponent positionComponent = attackAnimation.getComponentByClass(PositionComponent.class);
				Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
				
				room.getCell(positionComponent.getPosition()).getAnimations().remove(attackAnimation);
				Engine.getInstance().removeEntity(attackAnimation);
			}
		}
	}
}
