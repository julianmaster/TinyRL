package screens;

import java.awt.event.KeyEvent;

import generator.EntityGenerator;
import main.TinyRL;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import ui.AsciiPanel;

public class InventoryScreenComponent extends ScreenComponent {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ScreenEvent) {
			Entity inventoryScreen = Engine.getInstance().getEntityByComponent(this);
			/**
			 * UPDATE
			 */
			KeyEvent event = TinyRL.getInstance().getAsciiTerminal().getEvent();
			if(event != null) {
				if(event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Engine.getInstance().removeEntity(inventoryScreen);
					Engine.getInstance().addEntity(EntityGenerator.newPlayScreen());
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
			}
			
			KeyEvent otherEvent = TinyRL.getInstance().getAsciiTerminal().getOtherEvent();
			if(otherEvent != null) {
				
			}
			
			/**
			 * DRAW
			 */
			
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			asciiPanel.clear();
		}
	}
}
