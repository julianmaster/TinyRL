package model;

import model.entities.Entity;

public interface TurnHandler {
	public void init(Entity entity);
	public boolean turn();
}
