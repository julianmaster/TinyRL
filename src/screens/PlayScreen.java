package screens;

import java.awt.event.KeyEvent;

import ui.AsciiPanel;
import ui.CustomColor;

import main.TinyRL;
import model.World;

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
		TinyRL.getInstance().getWorld().getCurrentRoom().draw();
		
		AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
		
		if(pause) {
			asciiPanel.writeString(10, 0, "PAUSE", CustomColor.LBLUE, CustomColor.GREEN);
		}
	}
}
