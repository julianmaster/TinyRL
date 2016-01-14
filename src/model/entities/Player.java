package model.entities;

import model.Energy;
import model.Tile;
import model.turns.PlayerTurnHandler;

public class Player extends Entity {
	public Player() {
		super(Tile.PLAYER, new PlayerTurnHandler(), new Energy(60));
	}
}
