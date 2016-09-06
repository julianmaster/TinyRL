package model.animations;

import pattern.Event;

public class RainAnimationTileComponent extends AnimationTileComponent {
	
	public RainAnimationTileComponent() {
		super(AnimationTile.RAIN1);
	}
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof AnimationTileEvent) {
			AnimationTileEvent animationTileEvent = (AnimationTileEvent)e;
			animationTile = animationTileEvent.getAnimationTile();
		}
	}
}
