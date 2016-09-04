package model.animations;

import pattern.Event;

public class RainAnimationComponent extends AnimationTileComponent {
	
	public RainAnimationComponent() {
		super(AnimationTile.RAIN1);
	}
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RainAnimationComponent) {
			animationTile = AnimationTile.RAIN2;
		}
	}
}
