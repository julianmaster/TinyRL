package model.turns.entities;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.entities.AttributesComponent;
import model.entities.ResolveTurnEvent;
import model.turns.NextTickTurnControllerEvent;
import model.turns.TurnComponent;
import model.turns.actions.AttackActionEvent;
import model.turns.actions.ChangeRoomActionComponent;
import model.turns.actions.ChangeRoomActionEvent;
import model.turns.actions.MoveActionEvent;
import model.turns.actions.NextActionEvent;
import model.turns.actions.OpenActionComponent;
import model.turns.actions.OpenActionEvent;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import ui.CustomAsciiTerminal;
import util.Pair;

public class PlayerTurnComponent extends TurnComponent {
	
	public PlayerTurnComponent(int energyNeedToAction) {
		super(energyNeedToAction);
	}

	@Override
	public void process(Event e, double deltaTime) {
		super.process(e, deltaTime);
		
		if(e instanceof NextActionEvent) {
			NextActionEvent nextActionEvent = (NextActionEvent)e;
			
			CustomAsciiTerminal asciiTerminal = TinyRL.getInstance().getAsciiTerminal();
			Entity player = Engine.getInstance().getEntityByComponent(this);
			KeyEvent event = asciiTerminal.getEvent();
			
			if(event != null) {
				int dx = 0;
				int dy = 0;
				if(event.getKeyCode() == KeyEvent.VK_NUMPAD8) {
					dy = -1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD2) {
					dy = 1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					dx = -1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					dx = 1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD1) {
					dy = 1;
					dx = -1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD3) {
					dy = 1;
					dx = 1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD7) {
					dy = -1;
					dx = -1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD9) {
					dy = -1;
					dx = 1;
				}
				else if(event.getKeyCode() == KeyEvent.VK_NUMPAD5) {
					// IDLE
					Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
					asciiTerminal.setEvent(null);
				}
				
				if(nextActionEvent.getLastActionEvent() == null && (dx != 0 || dy != 0)) {
					Engine.getInstance().addHeadEvent(new MoveActionEvent(player, dx, dy));
					
					asciiTerminal.setEvent(null);
				}
			}
			
			if(nextActionEvent.getLastActionEvent() != null) {
				
				if(nextActionEvent.getLastActionEvent() instanceof MoveActionEvent) {
					MoveActionEvent moveActionEvent = (MoveActionEvent)nextActionEvent.getLastActionEvent();
					Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
					
					PositionComponent positionComponent = player.getComponentByClass(PositionComponent.class);

					Entity entity = room.getCell(new Pair<Integer, Integer>(positionComponent.getPosition().key + moveActionEvent.getDx(), positionComponent.getPosition().value + moveActionEvent.getDy())).getEntity();

					// Open action
					OpenActionComponent openActionComponent = entity.getComponentByClass(OpenActionComponent.class);
					if(openActionComponent != null) {
						if(!openActionComponent.isOpen()) {
							Engine.getInstance().addHeadEvent(new OpenActionEvent(entity));
						}
						else {
							// Change room action
							if(entity.asComponentOfClass(ChangeRoomActionComponent.class)) {
								Engine.getInstance().addTailEvent(new ChangeRoomActionEvent(entity, player));
							}
						}
					}
					
					// Attack action
					AttributesComponent enemyAttributesComponent = entity.getComponentByClass(AttributesComponent.class);
					if(enemyAttributesComponent != null) {
						Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
						Engine.getInstance().addHeadEvent(new AttackActionEvent(moveActionEvent.getEntity(), entity));
					}
					
					asciiTerminal.setEvent(null);
				}
			}
		}
	}
}
