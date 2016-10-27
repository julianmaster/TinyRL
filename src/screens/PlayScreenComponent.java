package screens;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.RenderRoomEvent;
import model.WorldTickEvent;
import model.entities.AttributesComponent;
import model.turns.entities.PlayerTurnComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import ui.AsciiPanel;
import ui.CustomColor;

public class PlayScreenComponent implements Component {
	
	public static final String HP = String.valueOf((char)3);
	public static final String MANA = String.valueOf((char)247);
	public static final String PHYSICAL_ARMOR = String.valueOf((char)31);
	public static final String MAGICAL_ARMOR = String.valueOf((char)31);
	
	boolean pause = false;
	boolean info = false;

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof PlayScreenEvent) {
			/**
			 * UPDATE
			 */
			
			// TODO Adding the animation blocking turn system.
			
			KeyEvent event = TinyRL.getInstance().getAsciiTerminal().getEvent();
			if(event != null) {
				if(event.getKeyCode() == KeyEvent.VK_SPACE) {
					pause = !pause;
					
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
			}
			
			KeyEvent otherEvent = TinyRL.getInstance().getAsciiTerminal().getOtherEvent();
			if(otherEvent != null) {
				
				// Show help info
				if(otherEvent.getKeyCode() == KeyEvent.VK_COMMA && otherEvent.getID() == KeyEvent.KEY_PRESSED && info == false) {
					info = true;
				}
				// Hidden help info
				else if(otherEvent.getKeyCode() == KeyEvent.VK_COMMA && otherEvent.getID() == KeyEvent.KEY_RELEASED && info == true) {
					info = false;
				}
				
				TinyRL.getInstance().getAsciiTerminal().setOtherEvent(null);
			}
			
			if(!pause) {
				// Animation update
				Engine.getInstance().addTailEvent(new WorldTickEvent());
			}
			
			
			
			
			/**
			 * DRAW
			 */
			
//			Room currentRoom = TinyRL.getInstance().getWorld().getCurrentRoom();
//			currentRoom.draw();
			Engine.getInstance().addTailEvent(new RenderRoomEvent());
			
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			asciiPanel.clear();
			
			// Paint pause
			if(pause) {
				asciiPanel.writeString(12, 0, "PAUSE", CustomColor.VIKING, CustomColor.ELF_GREEN);
			}
			// Paint help info
			else if(info) {
				asciiPanel.writeString(10, 1, HP + " HP", CustomColor.BROWN);
				asciiPanel.writeString(10, 2, MANA + " MANA", CustomColor.ROYAL_BLUE);
				asciiPanel.writeString(10, 3, PHYSICAL_ARMOR + " PH ARMOR", CustomColor.GOLDEN_FIZZ);
				asciiPanel.writeString(10, 4, MAGICAL_ARMOR + " MA ARMOR", CustomColor.VIKING);
			}
			// Paint player info
			else{
				Entity player = Engine.getInstance().getEntityByComponentClass(PlayerTurnComponent.class);
				AttributesComponent attributesComponent = player.getComponentByClass(AttributesComponent.class);
				
				asciiPanel.writeString(10, 1, HP + String.format(" %3.0f",attributesComponent.getHp()) + "/" + String.format("%.0f",attributesComponent.getHpMax()), CustomColor.BROWN);
				asciiPanel.writeString(10, 2, MANA + String.format(" %3.0f",attributesComponent.getMana()) + "/" + String.format("%.0f",attributesComponent.getManaMax()), CustomColor.ROYAL_BLUE);
				asciiPanel.writeString(10, 3, PHYSICAL_ARMOR + String.format(" %2s",attributesComponent.getPhysicalArmor()), CustomColor.GOLDEN_FIZZ);
				asciiPanel.writeString(10, 4, MAGICAL_ARMOR + String.format(" %2s",attributesComponent.getMagicalArmor()), CustomColor.VIKING);
				asciiPanel.writeString(10, 8, "P:INV", CustomColor.WHITE);
				asciiPanel.writeString(19, 8, "?", CustomColor.WHITE);
			}
		}
	}
}
