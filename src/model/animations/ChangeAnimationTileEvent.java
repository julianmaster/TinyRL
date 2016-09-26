package model.animations;

import pattern.Entity;
import pattern.EntityComponentEvent;

public class ChangeAnimationTileEvent extends EntityComponentEvent {
	
	private AnimationTile animationTile;

	public ChangeAnimationTileEvent(Entity entity, AnimationTile animationTile) {
		super(AnimationTileComponent.class, entity);
		this.animationTile = animationTile;
	}
	
	public AnimationTile getAnimationTile() {
		return animationTile;
	}
}
