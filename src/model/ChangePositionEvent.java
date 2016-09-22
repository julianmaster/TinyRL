package model;

import pattern.Entity;
import pattern.EntityComponentEvent;
import util.Pair;

public class ChangePositionEvent extends EntityComponentEvent {
	
	private int x;
	private int y;

	public ChangePositionEvent(Entity entity, Pair<Integer, Integer> newPosition) {
		super(PositionComponent.class, entity);
		this.x = newPosition.key;
		this.y = newPosition.value;
	}
	
	public ChangePositionEvent(Entity entity, int x, int y) {
		super(PositionComponent.class, entity);
		this.x = x;
		this.y = y;
	}
	
	public Pair<Integer, Integer> getNewPosition() {
		return new Pair<Integer, Integer>(x, y);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
