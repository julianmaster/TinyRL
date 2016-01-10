package model.entities;

import model.Energy;
import model.PlayerInputHandler;
import model.Tile;

public class Player extends Entity {
	public Player() {
		super(Tile.PLAYER, new PlayerInputHandler(), new Energy(60));
	}
}
