package main;

import java.awt.Dimension;

import generator.EntityGenerator;
import model.World;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import screens.PlayScreenEvent;
import ui.AsciiPanel;
import ui.CustomAsciiTerminal;
import ui.CustomColor;

// Regarder : https://www.quora.com/How-do-you-make-a-GIF-file-in-Java-using-an-array-of-BufferedImages

public class TinyRL {
	public static final String TITLE = "TinyRL";
	public static final int WINDOW_WIDTH = 20;
	public static final int WINDOW_HEIGHT = 9;
	public static final String ICON_FILE = "/assets/icon.png";
	
	//*
	public static final String TILESET_FILE = "/assets/wanderlust.png";
	public static final int CHARACTER_WIDTH = 12;
	public static final int CHARACTER_HEIGHT = 12;
	//*/
	/*
	public static final String TILESET_FILE = "/assets/Kein.png";
	public static final int CHARACTER_WIDTH = 5;
	public static final int CHARACTER_HEIGHT = 5;
	//*/
	/*
	public static final String TILESET_FILE = "/assets/taffer_original.png";
	public static final int CHARACTER_WIDTH = 10;
	public static final int CHARACTER_HEIGHT = 10;
	//*/
	/*
	public static final String TILESET_FILE = "/assets/taffer_blackletter.png";
	public static final int CHARACTER_WIDTH = 10;
	public static final int CHARACTER_HEIGHT = 10;
	//*/

	public static final int SCALE = 2;
	public static final boolean CUSTOM_WINDOW = true;
	
	public static final int TARGET_FPS = 60;
	public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	
	private static TinyRL instance = new TinyRL();
	
	private CustomAsciiTerminal asciiTerminal;
	private AsciiPanel asciiPanel;
	private World world;
	
	private Event currentEvent;
	
	private TinyRL() {
		asciiTerminal = new CustomAsciiTerminal(TITLE, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT), TILESET_FILE, CHARACTER_WIDTH, CHARACTER_HEIGHT, SCALE, ICON_FILE, CUSTOM_WINDOW);
		asciiPanel = asciiTerminal.getAsciiPanel();
		asciiPanel.setDefaultCharacterBackgroundColor(CustomColor.BLACK);
		asciiPanel.setDefaultCharacterColor(CustomColor.WHITE);
		
		world = EntityGenerator.newWorld();
		Engine.getInstance().addEntity(world);
		
		init();
		
		asciiPanel.write(0, 0, 'A', CustomColor.BLACK);
		asciiPanel.write(0, 1, 'B', CustomColor.VALHALLA);
		asciiPanel.write(0, 2, 'C', CustomColor.LOULOU);
		asciiPanel.write(0, 3, 'D', CustomColor.OILED_CEDAR);
		asciiPanel.write(0, 4, 'E', CustomColor.ROPE);
		asciiPanel.write(0, 5, 'F', CustomColor.TAHITI_GOLD);
		asciiPanel.write(0, 6, 'G', CustomColor.TWINE);
		asciiPanel.write(0, 7, 'H', CustomColor.PANCHO);
		asciiPanel.write(1, 0, 'I', CustomColor.GOLDEN_FIZZ);
		asciiPanel.write(1, 1, 'J', CustomColor.ATLANTIS);
		asciiPanel.write(1, 2, 'K', CustomColor.CHRISTI);
		asciiPanel.write(1, 3, 'L', CustomColor.ELF_GREEN);
		asciiPanel.write(1, 4, 'M', CustomColor.DELL);
		asciiPanel.write(1, 5, 'N', CustomColor.VERDIGRIS);
		asciiPanel.write(1, 6, 'O', CustomColor.OPAL);
		asciiPanel.write(1, 7, 'P', CustomColor.DEEP_KOAMARU);
		asciiPanel.write(2, 0, 'Q', CustomColor.VENICE_BLUE);
		asciiPanel.write(2, 1, 'R', CustomColor.ROYAL_BLUE);
		asciiPanel.write(2, 2, 'S', CustomColor.CORNFLOWER);
		asciiPanel.write(2, 3, 'T', CustomColor.VIKING);
		asciiPanel.write(2, 4, 'U', CustomColor.LIGHT_STEEL_BLUE);
		asciiPanel.write(2, 5, 'V', CustomColor.WHITE);
		asciiPanel.write(2, 6, 'W', CustomColor.HEATHER);
		asciiPanel.write(2, 7, 'X', CustomColor.TOPAZ);
		asciiPanel.write(3, 0, 'Y', CustomColor.DIM_GRAY);
		asciiPanel.write(3, 1, 'Z', CustomColor.SMOKEY_ASH);
		asciiPanel.write(3, 2, '@', CustomColor.CLAIRVOYANT);
		asciiPanel.write(3, 3, '&', CustomColor.BROWN);
		asciiPanel.write(3, 4, '#', CustomColor.MANDY);
		asciiPanel.write(3, 5, '$', CustomColor.PLUM);
		asciiPanel.write(3, 6, (char)224, CustomColor.RAIN_FOREST);
		asciiPanel.write(3, 7, (char)225, CustomColor.STINGER);
		asciiTerminal.repaint();
	}
	
	private void init() {
		Entity playScreen = EntityGenerator.newPlayScreen();
		Engine.getInstance().addEntity(playScreen);
		
		currentEvent = new PlayScreenEvent();
	}
	
	public void run() {
		long lastLoopTime = System.nanoTime();
		
		while(true) {
			long now = System.nanoTime();
			double updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / TinyRL.OPTIMAL_TIME;
			
			Engine.getInstance().addTailEvent(currentEvent);
			Engine.getInstance().process(null, delta);
			
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
