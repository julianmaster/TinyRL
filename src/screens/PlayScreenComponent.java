package screens;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.GeneralEvent;
import model.Room;
import model.World;
import model.entities.Player;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import ui.AsciiPanel;
import ui.CustomColor;
import util.Pair;

public class PlayScreenComponent implements Component {
	
	public static final String HP = String.valueOf((char)3);
	public static final String MANA = String.valueOf((char)247);
	public static final String ARMOR = String.valueOf((char)31);
	
	boolean pause = false;
	boolean info = false;

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof PlayScreenEvent) {
			/**
			 * UPDATE
			 */
			
			World world = TinyRL.getInstance().getWorld();
			
			// TODO Adding the animation blocking turn system.
			
			KeyEvent event = TinyRL.getInstance().getAsciiTerminal().getEvent();
			if(event != null) {
				if(event.getKeyCode() == KeyEvent.VK_SPACE) {
					pause = !pause;
					
					Engine engine = Engine.getInstance();
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
					System.out.println("Error - "+this.getClass().getName());
				}
				else if(event.getKeyCode() == KeyEvent.VK_A) {
					Room currentRoom = TinyRL.getInstance().getWorld().getCurrentRoom();
					Pair<Integer, Integer> playerPosition = currentRoom.getPositionOfEntityType(Player.class).get(0);
					Player player = (Player) currentRoom.getCell(playerPosition).getEntity();
					
					player.setHp(player.getHp()+1);
					
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
				else if(event.getKeyCode() == KeyEvent.VK_Z) {
					Room currentRoom = TinyRL.getInstance().getWorld().getCurrentRoom();
					Pair<Integer, Integer> playerPosition = currentRoom.getPositionOfEntityType(Player.class).get(0);
					Player player = (Player) currentRoom.getCell(playerPosition).getEntity();
					
					player.setHp(player.getHp()+1);
					
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
				Engine.getInstance().addTailEvent(new GeneralEvent());
				
				// Turn update
//				world.getTurnController().update();
			}
			
			
			
			
			/**
			 * DRAW
			 */
			
			Room currentRoom = TinyRL.getInstance().getWorld().getCurrentRoom();
			currentRoom.draw();
			
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			
			// Paint pause
			if(pause) {
				asciiPanel.writeString(12, 0, "PAUSE", CustomColor.LBLUE, CustomColor.GREEN);
			}
			// Paint help info
			else if(info) {
				asciiPanel.writeString(10, 1, HP + " HP", CustomColor.RED);
				asciiPanel.writeString(10, 2, MANA + " MANA", CustomColor.LBLUE);
				asciiPanel.writeString(10, 3, ARMOR + " ARMOR", CustomColor.YELLOW);
			}
			// Paint player info
//			else{
//				Pair<Integer, Integer> playerPosition = currentRoom.getPositionOfEntityType(Player.class).get(0);
//				Player player = (Player) currentRoom.getCell(playerPosition).getEntity();
//				
//				asciiPanel.writeString(10, 1, HP + String.format(" %3.0f",player.getHp()) + "/" + String.format("%.0f",player.getHpMax()), CustomColor.RED);
//				asciiPanel.writeString(10, 2, MANA + String.format(" %3.0f",player.getMana()) + "/" + String.format("%.0f",player.getManaMax()), CustomColor.LBLUE);
//				asciiPanel.writeString(10, 3, ARMOR + String.format(" %3s",player.getArmor()), CustomColor.YELLOW);
//				asciiPanel.writeString(10, 8, "P:INV", CustomColor.WHITE);
//				asciiPanel.writeString(19, 8, "?", CustomColor.WHITE);
//			}
		}
	}
}
