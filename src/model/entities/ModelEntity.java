package model.entities;

import model.Energy;
import pattern.Entity;

public class ModelEntity extends Entity {
	private Energy energy;

	public ModelEntity(Energy energy) {
		this.energy = energy;
	}

	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
}
