package model.animations;

import pattern.Component;
import pattern.Event;

public class AnimationLevelComponent implements Component {
	private int level;
	
	public AnimationLevelComponent(int level) {
		this.level = level;
	}

	@Override
	public void process(Event e, double deltaTime) {
	}
	
	public int getLevel() {
		return level;
	}
}
