package model.turns;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.PositionComponent;
import model.World;
import model.entities.ModelEntity;
import model.turns.actions.MoveActionEvent;
import model.turns.actions.NextActionEvent;
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
				
				if(nextActionEvent.getLastActionEvent() == null) {
					Engine.getInstance().addHeadEvent(new MoveActionEvent(player, dx, dy));
					
					asciiTerminal.setEvent(null);
				}
			}
		}
	}
}
