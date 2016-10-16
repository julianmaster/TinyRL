package model.entities;

import java.util.List;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class TakeDamageEvent extends EntityComponentEvent {
	
	private List<Integer> damages;

	public TakeDamageEvent(Entity entity, List<Integer> damages) {
		super(AttributesComponent.class, entity);
		this.damages = damages;
	}
	
	public List<Integer> getDamages() {
		return damages;
	}
}
