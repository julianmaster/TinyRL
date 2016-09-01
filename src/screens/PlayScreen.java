package screens;

import java.awt.event.KeyEvent;

import main.TinyRL;
import model.Room;
import model.World;
import model.entities.Player;
import ui.AsciiPanel;
import ui.CustomColor;
import util.Pair;

public class PlayScreen implements Screen {
	
	boolean pause = false;

	@Override
	public void update(double delta) {
		World world = TinyRL.getInstance().getWorld();
		
		// TODO Adding the animation blocking turn system.
		
		KeyEvent event = TinyRL.getInstance().getAsciiTerminal().getEvent();
		if(event != null) {
			if(event.getKeyCode() == KeyEvent.VK_SPACE) {
				pause = !pause;
				
				TinyRL.getInstance().getAsciiTerminal().setEvent(null);
			}
		}
		
		if(!pause) {
			// Animation update
			world.getAnimationController().update(delta);
			
			// Turn update
			world.getTurnController().update();
		}
	}

	@Override
	public void paint() {
		Room currentRoom = TinyRL.getInstance().getWorld().getCurrentRoom();
		currentRoom.draw();
		
		AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
		
		if(pause) {
			asciiPanel.writeString(10, 0, "PAUSE", CustomColor.LBLUE, CustomColor.GREEN);
		}
		else{
			Pair<Integer, Integer> playerPosition = currentRoom.getPositionOfEntityType(Player.class).get(0);
			Player player = (Player) currentRoom.getCell(playerPosition).getEntity();
			
			asciiPanel.writeString(10, 1, String.format("%.0f",player.getHpPoint()), CustomColor.RED);
		}
	}
}
