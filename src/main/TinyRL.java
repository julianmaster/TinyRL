package main;

import java.awt.Dimension;

import model.World;
import ui.CustomColor;
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
		terminal.setDefaultCharacterBackgroundColor(CustomColor.BLACK);
		terminal.setDefaultCharacterColor(CustomColor.WHITE);
		world = new World();
		world.run();
		
//		terminal.write(0, 0, 'A', CustomColor.BLUE);
//		terminal.write(0, 1, 'B', CustomColor.GREEN);
//		terminal.write(0, 2, 'C', CustomColor.CYAN);
//		terminal.write(0, 3, 'D', CustomColor.RED);
//		terminal.write(0, 4, 'E', CustomColor.MAGENTA);
//		terminal.write(1, 0, 'F', CustomColor.BROWN);
//		terminal.write(1, 1, 'G', CustomColor.LGRAY);
//		terminal.write(1, 2, 'H', CustomColor.DGRAY);
//		terminal.write(1, 3, 'I', CustomColor.LBLUE);
//		terminal.write(1, 4, 'J', CustomColor.LGREEN);
//		terminal.write(2, 0, 'K', CustomColor.LCYAN);
//		terminal.write(2, 1, 'L', CustomColor.LRED);
//		terminal.write(2, 2, 'M', CustomColor.LMAGENTA);
//		terminal.write(2, 3, 'N', CustomColor.YELLOW);
//		terminal.write(2, 4, 'O', CustomColor.WHITE);
	}
	
	public static void main(String[] args) {
		new TinyRL();
	}
}
