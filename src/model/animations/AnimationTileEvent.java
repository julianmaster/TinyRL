package model.animations;

import pattern.Component;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class AnimationTileEvent extends EntityComponentEvent {

	public AnimationTileEvent(Entity entity) {
		super(AnimationTileComponent.class, entity);
	}
}
