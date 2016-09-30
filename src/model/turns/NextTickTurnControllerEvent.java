package model.turns;

import pattern.ComponentEvent;

public class NextTickTurnControllerEvent extends ComponentEvent {

	public NextTickTurnControllerEvent() {
		super(TurnControllerComponent.class);
	}
}
