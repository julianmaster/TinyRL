package model.entities;

import util.Pair;
import model.PositionComponent;
import model.turns.PlayerTurnComponent;
import model.turns.actions.MoveActionComponent;

public class ModelEntities {
	
	public static ModelEntity newPlayer(Pair<Integer, Integer> position) {
		Player player = new Player();
		
		player.add(new PlayerTurnComponent());
		player.add(new PositionComponent(position));
		player.add(new MoveActionComponent());
		
		return player;
	}
}
