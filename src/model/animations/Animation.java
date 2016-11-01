package model.animations;

import pattern.Entity;

public class Animation extends Entity implements Comparable<Animation> {
	@Override
	public int compareTo(Animation o) {
		AnimationLevelComponent animationLevelComponent = this.getComponentByClass(AnimationLevelComponent.class);
		AnimationLevelComponent otherAnimationLevelComponent = o.getComponentByClass(AnimationLevelComponent.class);
		
		return animationLevelComponent.getLevel() - otherAnimationLevelComponent.getLevel();
	}
}