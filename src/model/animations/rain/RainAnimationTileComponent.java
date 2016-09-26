package model.animations.rain;

import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import model.animations.ChangeAnimationTileEvent;
import pattern.Event;

public class RainAnimationTileComponent extends AnimationTileComponent {
	
	public RainAnimationTileComponent() {
		super(AnimationTile.RAIN1);
	}
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeAnimationTileEvent) {
			ChangeAnimationTileEvent animationTileEvent = (ChangeAnimationTileEvent)e;
			animationTile = animationTileEvent.getAnimationTile();
		}
	}
}
