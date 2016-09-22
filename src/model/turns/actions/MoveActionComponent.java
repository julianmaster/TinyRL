package model.turns.actions;

import main.TinyRL;
import model.ChangePositionEvent;
import model.PositionComponent;
import model.World;
import model.entities.ModelEntity;
import model.turns.TurnControllerNextTurnEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class MoveActionComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof MoveActionEvent) {
			MoveActionEvent moveActionEvent = (MoveActionEvent)e;
			World world = TinyRL.getInstance().getWorld();
			
			ModelEntity player = (ModelEntity)Engine.getInstance().getEntityByComponent(this);
			PositionComponent positionComponent = player.getComponentByClass(PositionComponent.class);
			
			Pair<Integer, Integer> positionPlayer = positionComponent.getPosition();
			Pair<Integer, Integer> positionTarget = positionPlayer.clone();
			
			positionTarget.key += moveActionEvent.getDx();
			positionTarget.value += moveActionEvent.getDy();
			
			if(world.getCurrentRoom().getCell(positionTarget).getEntity() == null) {
				world.getCurrentRoom().getCell(positionPlayer).setEntity(null);
				world.getCurrentRoom().getCell(positionTarget).setEntity(player);
				
				Engine.getInstance().addHeadEvent(new TurnControllerNextTurnEvent());
				Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, positionTarget));
			}
			else {
				Engine.getInstance().addHeadEvent(new NextActionEvent(player, moveActionEvent));
			}
		}
	}
}
