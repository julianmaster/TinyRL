package model;

import pattern.Entity;
import pattern.EntityComponentEvent;
import util.Pair;

public class ChangeXYPositionEvent extends EntityComponentEvent {
	
	private Pair<Integer, Integer> newPosition;

	public ChangeXYPositionEvent(Entity entity, Pair<Integer, Integer> newPosition) {
		super(PositionComponent.class, entity);
		this.newPosition = newPosition;
	}
	
	public ChangeXYPositionEvent(Entity entity, int x, int y) {
		super(PositionComponent.class, entity);
		this.newPosition = new Pair<Integer, Integer>(x, y);
	}
	
	public Pair<Integer, Integer> getNewPosition() {
		return newPosition;
	}
}
