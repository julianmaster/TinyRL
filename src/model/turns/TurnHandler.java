package model.turns;

import model.entities.ModelEntity;

public interface TurnHandler {
	public void init(ModelEntity entity);
	public boolean turn();
}
