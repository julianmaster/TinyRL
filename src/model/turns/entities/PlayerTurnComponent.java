package model.turns.entities;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.turns.TurnComponent;
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
					
					asciiTerminal.setEvent(null);
				}
			}
		}
	}
}
