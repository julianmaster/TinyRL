package model.entities;

import model.Energy;
import model.Tile;
import model.turns.TurnHandler;

public class Entity {
	private Tile tile;
	private TurnHandler turnHandler;
	private Energy energy;

	public Entity(Tile tile, TurnHandler turnHandler, Energy energy) {
		this.tile = tile;
		this.turnHandler = turnHandler;
		this.energy = energy;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public TurnHandler getTurnHandler() {
		return turnHandler;
	}
	
	public void setTurnHandler(TurnHandler turnHandler) {
		this.turnHandler = turnHandler;
	}

	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
}
