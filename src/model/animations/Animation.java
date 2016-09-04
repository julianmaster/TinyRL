package model.animations;

import pattern.Component;
import pattern.Entity;


//public abstract class Animation implements Comparable<Animation> {
//	protected AnimationTile tile;
//	protected Integer level;
//
//	public Animation(AnimationTile tile, Integer level) {
//		this.tile = tile;
//		this.level = level;
//	}
//
//	public abstract boolean update(double delta);
//	
//	public abstract boolean done();
//
//	@Override
//	public int compareTo(Animation o) {
//		return level.compareTo(o.getLevel());
//	}
//	
//	public AnimationTile getTile() {
//		return tile;
//	}
//	
//	public Integer getLevel() {
//		return level;
//	}
//}

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