package screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import generator.EntityGenerator;
import main.TinyRL;
import model.items.ItemRarity;
import model.items.RainbowColorControllerEvent;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import ui.AsciiPanel;
import ui.CustomColor;

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
			Engine.getInstance().addHeadEvent(new RainbowColorControllerEvent());
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			asciiPanel.clear();
			
			Color bagColor = CustomColor.LOULOU;
			asciiPanel.write(2, 2, (char)201, bagColor);
			asciiPanel.write(5, 2, (char)187, bagColor);
			asciiPanel.write(2, 6, (char)200, bagColor);
			asciiPanel.write(5, 6, (char)188, bagColor);
			for(int i = 0; i < 2; i++) {
				asciiPanel.write(3+i, 2, (char)205, bagColor);
				asciiPanel.write(3+i, 6, (char)205, bagColor);
			}
			for(int j = 0; j < 3; j++) {
				asciiPanel.write(2, 3+j, (char)186, bagColor);
				asciiPanel.write(5, 3+j, (char)186, bagColor);
			}
			
			
			asciiPanel.write(3, 3, '&', ItemRarity.RARE_ITEM.color);
			asciiPanel.write(4, 3, '&', CustomColor.WHITE);
			asciiPanel.write(3, 4, '&', CustomColor.WHITE);
			asciiPanel.write(4, 4, '&', CustomColor.WHITE);
			asciiPanel.write(3, 5, '&', CustomColor.HEATHER);
			asciiPanel.write(4, 5, '&', CustomColor.HEATHER);
			
			
			asciiPanel.writeString(0, TinyRL.WINDOW_HEIGHT-1, "ESC:exit", CustomColor.WHITE);
			asciiPanel.writeString(TinyRL.WINDOW_WIDTH/2-5, 0, "Inventory", CustomColor.WHITE);
			
		}
	}
}
