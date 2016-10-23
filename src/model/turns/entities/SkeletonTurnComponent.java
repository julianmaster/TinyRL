package model.turns.entities;

import java.util.ArrayList;

import main.TinyRL;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.animations.attack.AddAttackAnimationEvent;
import model.entities.KillEvent;
import model.turns.NextTickTurnControllerEvent;
import model.turns.TurnComponent;
import model.turns.actions.AttackActionComponent;
import model.turns.actions.AttackActionEvent;
import model.turns.actions.MoveActionEvent;
import model.turns.actions.NextActionEvent;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

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
			
			ArrayList<Pair<Integer, Integer>> path = currentRoom.pathTo(positionComponent.getPosition(), playerPositionComponent.getPosition());
			
			if(path == null) {
				return;
			}
			
			if(path.size() > 2) {
				System.out.println("SkeletonTurnComponent: move");
				Pair<Integer, Integer> nextStep = path.get(1);
				Engine.getInstance().addHeadEvent(new MoveActionEvent(skeleton, nextStep.key - positionComponent.getPosition().key, nextStep.value - positionComponent.getPosition().value));
			}
			else {
				System.out.println("SkeletonTurnComponent: attack");
				Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
				Engine.getInstance().addHeadEvent(new AttackActionEvent(skeleton, player));
				Engine.getInstance().addHeadEvent(new AddAttackAnimationEvent(player));
			}
		}
	}
}
