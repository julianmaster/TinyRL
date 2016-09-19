package model.entities;

import model.Energy;
import model.Tile;
import pattern.Entity;

public class ModelEntity extends Entity {
	private Tile tile;
	private Energy energy;

	public ModelEntity(Tile tile, Energy energy) {
		this.tile = tile;
		this.energy = energy;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
}
