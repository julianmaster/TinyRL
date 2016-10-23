package model.animations.attack;

import generator.EntityGenerator;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.animations.Animation;
import model.entities.EntityTileComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class AttackAnimationHandlerComponent implements Component {
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof AddAttackAnimationEvent) {
			AddAttackAnimationEvent addAttackAnimationEvent = (AddAttackAnimationEvent)e;
			Entity entity = addAttackAnimationEvent.getAttackedEntity();
			PositionComponent positionComponent = entity.getComponentByClass(PositionComponent.class);
			EntityTileComponent tileEntity = entity.getComponentByClass(EntityTileComponent.class);
			
			Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
			
			Animation animation = EntityGenerator.newAttackAnimation(positionComponent.getPosition(), tileEntity.getTile().tile);
			Engine.getInstance().addEntity(animation);
			room.getCell(positionComponent.getPosition()).getAnimations().add(animation);
		}
		if(e instanceof AttackAnimationEvent) {
			Engine.getInstance().addTailEvent(new TickAttackAnimationEvent());
		}
	}
}
