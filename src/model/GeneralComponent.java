package model;

import model.animations.rain.RainHandlerEvent;
import model.turns.TurnControllerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class GeneralComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof GeneralEvent) {
			Engine.getInstance().addTailEvent(new RainHandlerEvent());
			Engine.getInstance().addTailEvent(new TurnControllerEvent());
		}
	}
}
