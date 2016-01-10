package model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloseHandler extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
