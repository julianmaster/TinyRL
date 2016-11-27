package ui;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.TinyRL;

public class CustomAsciiTerminal extends AsciiTerminal {

	private KeyEvent event;
	private KeyEvent otherEvent;
	
	public CustomAsciiTerminal(String title, Dimension dimension, String tilesetFile, int characterWidth, int characterHeight, int scale, String frameIconPath, boolean customWindow) {
		super(title, dimension, tilesetFile, characterWidth, characterHeight, scale, customWindow);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		try {
			this.setIconImage(ImageIO.read(getClass().getResource(frameIconPath)));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.addWindowListener(new WindowCloseHandler());
		
		// KeyAdapter
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				event = e;
				otherEvent = e;
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				otherEvent = e;
			}
		});
	}

	public KeyEvent getEvent() {
		return event;
	}
	
	public void setEvent(KeyEvent event) {
		this.event = event;
	}
	
	public KeyEvent getOtherEvent() {
		return otherEvent;
	}
	
	public void setOtherEvent(KeyEvent otherEvent) {
		this.otherEvent = otherEvent;
	}
}
