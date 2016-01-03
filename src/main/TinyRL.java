package main;

import java.awt.Dimension;

import model.World;
import ui.Terminal;

public class TinyRL {
	public static final String TITLE = "TinyRL";
	public static final int WINDOW_WIDTH = 9;
	public static final int WINDOW_HEIGHT = 9;
	public static final String TILESET_FILE = "src/assets/wanderlust.png";
	public static final int CHARACTER_WIDTH = 12;
	public static final int CHARACTER_HEIGHT = 12;
	
	public static Terminal terminal;
	public static World world;
	
	public TinyRL() {
		terminal = new Terminal(TITLE, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT), TILESET_FILE, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		world = new World();
		world.run();
	}
	
	public static void main(String[] args) {
		new TinyRL();
	}
}
