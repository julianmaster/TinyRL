package model.animations;

import pattern.Component;
import pattern.Entity;
import pattern.EntityComponentEvent;

public class AnimationTileEvent extends EntityComponentEvent {
	
	private AnimationTile animationTile;

	public AnimationTileEvent(Entity entity, AnimationTile animationTile) {
		super(AnimationTileComponent.class, entity);
		this.animationTile = animationTile;
	}
	
	public AnimationTile getAnimationTile() {
		return animationTile;
	}
}
