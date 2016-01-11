package model.entities;

import model.Energy;
import model.PlayerTurnHandler;
import model.Tile;

public class Player extends Entity {
	public Player() {
		super(Tile.PLAYER, new PlayerTurnHandler(), new Energy(60));
	}
}
