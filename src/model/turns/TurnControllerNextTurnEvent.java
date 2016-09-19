package model.turns;

import pattern.ComponentEvent;

public class TurnControllerNextTurnEvent extends ComponentEvent {

	public TurnControllerNextTurnEvent() {
		super(TurnControllerComponent.class);
	}
}
