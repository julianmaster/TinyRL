package model.animations;

import pattern.Component;
import pattern.Entity;

public class Animation extends Entity implements Comparable<Animation> {
	@Override
	public int compareTo(Animation o) {
		for(Component c : this) {
			if(c instanceof AnimationLevelComponent) {
				return ((AnimationLevelComponent)c).getLevel();
			}
		}
		
		return 0;
	}
}