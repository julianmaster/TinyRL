package model.turns;

import pattern.ComponentEvent;

public class TurnControllerClearEntitiesEvent extends ComponentEvent {

	public TurnControllerClearEntitiesEvent() {
		super(TurnControllerComponent.class);
	}
}
