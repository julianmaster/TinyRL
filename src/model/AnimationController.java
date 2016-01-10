package model;

import model.animations.Animation;

public class AnimationController {
	private Animation animation;
	
	public void addAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public void process() {
		if(animation != null) {
			animation.update();
		}
	}
	
	public boolean done() {
		if(animation != null) {
			return animation.done();
		}
		return true;
	}
}
