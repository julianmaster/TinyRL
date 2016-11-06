package model;

import model.animations.attack.AttackAnimationEvent;
import model.animations.rain.RainHandlerEvent;
import model.items.RainbowColorControllerEvent;
import model.turns.TurnControllerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

public class WorldTickComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof WorldTickEvent) {
			Engine.getInstance().addTailEvent(new RainbowColorControllerEvent());
			Engine.getInstance().addTailEvent(new AttackAnimationEvent());
			Engine.getInstance().addTailEvent(new RainHandlerEvent());
			Engine.getInstance().addTailEvent(new TurnControllerEvent());
		}
	}
}
