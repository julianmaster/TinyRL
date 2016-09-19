package model.turns;

import pattern.Entity;

public class TurnControllerEntity {

	public static Entity newTurnController() {
		Entity turnController = new Entity();
		
		turnController.add(new TurnControllerComponent());
		
		return turnController;
	}
}
