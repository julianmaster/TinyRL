package model;

public class Entity {
	private Tile tile;
	private InputHandler input;
	private Energy energy;

	public Entity(Tile tile, InputHandler input, Energy energy) {
		this.tile = tile;
		this.input = input;
		this.energy = energy;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public InputHandler getInput() {
		return input;
	}

	public void setInput(InputHandler input) {
		this.input = input;
	}

	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}
}
