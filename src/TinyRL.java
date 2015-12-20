import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Random;

import ui.Terminal;
import util.Observable;
import util.Observer;



/**
 * 
 * @author julien MAITRE
 */
public class TinyRL implements Observer {
	public static final String TITLE = "TinyRL";
	public static final int WINDOW_WIDTH = 15;
	public static final int WINDOW_HEIGHT = 15;
	public static final String TILESET_FILE = "src/assets/wanderlust.png";
	public static final int CHARACTER_WIDTH = 12;
	public static final int CHARACTER_HEIGHT = 12;
	
	public Terminal terminal;
	private Random rand = new Random();
	
	public TinyRL() {
		terminal = new Terminal(TITLE, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT), TILESET_FILE, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		terminal.addObserver(this);
	}
	
	@Override
	public void updateObserver(Observable observable) {
		KeyEvent event = terminal.getEvent();
		
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			terminal.clear();
			for(int i = 0; i < WINDOW_WIDTH; i++) {
				for(int j = 0; j < WINDOW_HEIGHT; j++) {
//					terminal.write(i, j, (char) rand.nextInt(256), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
					terminal.write(i, j, (char) rand.nextInt(256), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), Color.BLACK);
				}
			}
			terminal.repaint();
		}
	}
	
	public static void main(String[] args) {
		new TinyRL();
	}
}
