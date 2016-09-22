package model.turns.actions;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class MoveActionEvent extends EntityComponentEvent {
	
	private int dx;
	private int dy;

	public MoveActionEvent(Entity entity, int dx, int dy) {
		super(MoveActionComponent.class, entity);
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
}
