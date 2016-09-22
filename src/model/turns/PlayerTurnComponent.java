package model.turns;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.PositionComponent;
import model.entities.ModelEntity;
import model.turns.actions.ChangeRoomActionComponent;
import model.turns.actions.ChangeRoomActionEvent;
import model.turns.actions.MoveActionEvent;
import model.turns.actions.NextActionEvent;
import model.turns.actions.OpenActionComponent;
import model.turns.actions.OpenActionEvent;
import pattern.Engine;
import pattern.Event;
import ui.CustomAsciiTerminal;
import util.Pair;

public class PlayerTurnComponent extends TurnComponent {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof NextActionEvent) {
			NextActionEvent nextActionEvent = (NextActionEvent)e;
			
			CustomAsciiTerminal asciiTerminal = TinyRL.getInstance().getAsciiTerminal();
			ModelEntity player = (ModelEntity)Engine.getInstance().getEntityByComponent(this);
			KeyEvent event = asciiTerminal.getEvent();
			
			if(event != null) {
				int dx = 0;
				int dy = 0;
				if(event.getKeyCode() == KeyEvent.VK_UP) {
					dy = -1;
				}
				if(event.getKeyCode() == KeyEvent.VK_DOWN) {
					dy = 1;
				}
				if(event.getKeyCode() == KeyEvent.VK_LEFT) {
					dx = -1;
				}
				if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
					dx = 1;
				}
				
				if(nextActionEvent.getLastActionEvent() == null && (dx != 0 || dy != 0)) {
					Engine.getInstance().addHeadEvent(new MoveActionEvent(player, dx, dy));
					
					asciiTerminal.setEvent(null);
				}
			}
			
			if(nextActionEvent.getLastActionEvent() != null) {
				
				if(nextActionEvent.getLastActionEvent() instanceof MoveActionEvent) {
					MoveActionEvent moveActionEvent = (MoveActionEvent)nextActionEvent.getLastActionEvent();
					
					PositionComponent positionComponent = player.getComponentByClass(PositionComponent.class);
					ModelEntity modelEntity = TinyRL.getInstance().getWorld().getCurrentRoom().getCell(new Pair<Integer, Integer>(positionComponent.getPosition().key + moveActionEvent.getDx(), positionComponent.getPosition().value + moveActionEvent.getDy())).getEntity();
					
					OpenActionComponent openActionComponent = modelEntity.getComponentByClass(OpenActionComponent.class);
					
					if(modelEntity.getComponentByClass(OpenActionComponent.class) != null && !openActionComponent.isOpen()) {
						Engine.getInstance().addHeadEvent(new OpenActionEvent(modelEntity));
					}
					else if(modelEntity.getComponentByClass(OpenActionComponent.class) != null && openActionComponent.isOpen()) {
						if(modelEntity.asComponentOfClass(ChangeRoomActionComponent.class)) {
							Engine.getInstance().addHeadEvent(new ChangeRoomActionEvent(modelEntity, player));
						}
					}
					
					asciiTerminal.setEvent(null);
				}
			}
		}
	}
}
