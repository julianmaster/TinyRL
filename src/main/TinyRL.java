package main;

import java.awt.Dimension;

import model.World;
import screens.PlayScreen;
import screens.Screen;
import ui.AsciiPanel;
import ui.CustomAsciiTerminal;
import ui.CustomColor;

// Regarder : https://www.quora.com/How-do-you-make-a-GIF-file-in-Java-using-an-array-of-BufferedImages

public class TinyRL {
	public static final String TITLE = "TinyRL";
	public static final int WINDOW_WIDTH = 20;
	public static final int WINDOW_HEIGHT = 9;
	public static final String TILESET_FILE = "/assets/wanderlust.png";
	public static final String ICON_FILE = "/assets/icon.png";
	public static final int CHARACTER_WIDTH = 12;
	public static final int CHARACTER_HEIGHT = 12;
	
	public static final int TARGET_FPS = 60;
	public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	
	private static TinyRL instance = new TinyRL();
	
	private CustomAsciiTerminal asciiTerminal;
	private AsciiPanel asciiPanel;
	private World world;
	
	private Screen currentScreen;
	
	private TinyRL() {
		asciiTerminal = new CustomAsciiTerminal(TITLE, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT), TILESET_FILE, CHARACTER_WIDTH, CHARACTER_HEIGHT, ICON_FILE);
		asciiPanel = asciiTerminal.getAsciiPanel();
		asciiPanel.setDefaultCharacterBackgroundColor(CustomColor.BLACK);
		asciiPanel.setDefaultCharacterColor(CustomColor.WHITE);
		world = new World();
		
		currentScreen = new PlayScreen();
		
//		asciiPanel.write(0, 0, 'A', CustomColor.BLUE);
//		asciiPanel.write(0, 1, 'B', CustomColor.GREEN);
//		asciiPanel.write(0, 2, 'C', CustomColor.CYAN);
//		asciiPanel.write(0, 3, 'D', CustomColor.RED);
//		asciiPanel.write(0, 4, 'E', CustomColor.MAGENTA);
//		asciiPanel.write(1, 0, 'F', CustomColor.BROWN);
//		asciiPanel.write(1, 1, 'G', CustomColor.LGRAY);
//		asciiPanel.write(1, 2, 'H', CustomColor.DGRAY);
//		asciiPanel.write(1, 3, 'I', CustomColor.LBLUE);
//		asciiPanel.write(1, 4, 'J', CustomColor.LGREEN);
//		asciiPanel.write(2, 0, 'K', CustomColor.LCYAN);
//		asciiPanel.write(2, 1, 'L', CustomColor.LRED);
//		asciiPanel.write(2, 2, 'M', CustomColor.LMAGENTA);
//		asciiPanel.write(2, 3, 'N', CustomColor.YELLOW);
//		asciiPanel.write(2, 4, 'O', CustomColor.WHITE);
//		asciiTerminal.repaint();
	}
	
	public void run() {
		long lastLoopTime = System.nanoTime();
		
		while(true) {
			long now = System.nanoTime();
			double updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / TinyRL.OPTIMAL_TIME;
			
			currentScreen.update(delta);
			
			// Paint
			currentScreen.paint();
			asciiTerminal.repaint();
			
			try {
				long value = (lastLoopTime - System.nanoTime() + TinyRL.OPTIMAL_TIME) / 1000000;
				if(value > 0) {
					Thread.sleep(value);					
				}
				else {
					Thread.sleep(5);
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static TinyRL getInstance() {
		return instance;
	}
	
	public World getWorld() {
		return world;
	}
	
	public CustomAsciiTerminal getAsciiTerminal() {
		return asciiTerminal;
	}
	
	public AsciiPanel getAsciiPanel() {
		return asciiPanel;
	}
	
	public static void main(String[] args) {
		TinyRL tinyRL = TinyRL.getInstance();
		tinyRL.run();
	}
}
