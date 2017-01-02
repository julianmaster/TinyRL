package screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import generator.EntityGenerator;
import main.TinyRL;
import model.entities.AttributesComponent;
import model.items.Item;
import model.items.ItemRarity;
import model.items.ItemRarityComponent;
import model.items.ItemTileComponent;
import model.items.RainbowColorControllerEvent;
import model.turns.entities.PlayerTurnComponent;
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
			Entity player = Engine.getInstance().getEntityByComponentClass(PlayerTurnComponent.class);
			AttributesComponent attributesComponent = player.getComponentByClass(AttributesComponent.class);
			
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
			
			// Item slot
			drawBorder(asciiPanel, 2, 2, 4, 4, CustomColor.OILED_CEDAR);
			// Weapon slot
			drawBorder(asciiPanel, 2, 2, 2, 2, CustomColor.OILED_CEDAR);
			asciiPanel.write(4, 2, (char)203, CustomColor.OILED_CEDAR);
			asciiPanel.write(2, 4, (char)204, CustomColor.OILED_CEDAR);
			
			Item weapon = attributesComponent.getWeapon();
			ItemTileComponent weaponTileComponent = weapon.getComponentByClass(ItemTileComponent.class);
			ItemRarityComponent weaponRarityComponent = weapon.getComponentByClass(ItemRarityComponent.class);
			asciiPanel.write(3, 3, weaponTileComponent.getItemTile().tile, weaponRarityComponent.getItemRarity().color, CustomColor.STINGER);
			
			asciiPanel.write(5, 3, ' ', CustomColor.WHITE, CustomColor.VALHALLA);
			asciiPanel.write(5, 4, ' ', CustomColor.WHITE, CustomColor.VALHALLA);
			asciiPanel.write(5, 5, ' ', CustomColor.WHITE, CustomColor.VALHALLA);
			asciiPanel.write(4, 5, ' ', CustomColor.WHITE, CustomColor.VALHALLA);
			asciiPanel.write(3, 5, ' ', CustomColor.WHITE, CustomColor.VALHALLA);
			
			
			asciiPanel.writeString(0, TinyRL.WINDOW_HEIGHT-1, "ESC:exit", CustomColor.WHITE);
			asciiPanel.writeString(TinyRL.WINDOW_WIDTH/2-5, 0, "Inventory", CustomColor.WHITE);
			
		}
	}
	
	public void drawBorder(AsciiPanel asciiPanel, int x, int y, int width, int height, Color color) {
		asciiPanel.write(x, y, (char)201, color);
		asciiPanel.write(x+width, y, (char)187, color);
		asciiPanel.write(x, y+height, (char)200, color);
		asciiPanel.write(x+width, y+height, (char)188, color);
		for(int i = 0; i < width-1; i++) {
			asciiPanel.write(x+1+i, y, (char)205, color);
			asciiPanel.write(x+1+i, y+height, (char)205, color);
		}
		for(int j = 0; j < height-1; j++) {
			asciiPanel.write(x, y+1+j, (char)186, color);
			asciiPanel.write(x+width, y+1+j, (char)186, color);
		}
	}
}
