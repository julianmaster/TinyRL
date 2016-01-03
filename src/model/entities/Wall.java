package model.entities;

import model.Entity;
import model.Tile;

public class Wall extends Entity {
	public Wall() {
		super(Tile.WALL, null, null);
	}
}
