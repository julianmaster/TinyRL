package model.animations.attack;

import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import pattern.Event;

public class AttackAnimationTileComponent extends AnimationTileComponent {
	
	public AttackAnimationTileComponent(AnimationTile animationTile, char tile) {
		super(new AnimationTile(tile, animationTile.color));
	}

	@Override
	public void process(Event e, double deltaTime) {
	}
}
