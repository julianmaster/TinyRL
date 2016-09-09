package model;

import model.animations.rain.RainHandlerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class GeneralComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		Engine.getInstance().processEvent(new RainHandlerEvent());
	}
}
