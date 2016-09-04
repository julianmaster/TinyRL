package model.animations;

import pattern.Component;

public abstract class AnimationTileComponent implements Component {
	protected AnimationTile animationTile;

	public AnimationTileComponent(AnimationTile animationTile) {
		this.animationTile = animationTile;
	}

	public AnimationTile getAnimationTile() {
		return animationTile;
	}
}
