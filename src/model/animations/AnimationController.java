package model.animations;

import java.util.ArrayList;
import java.util.List;

import main.TinyRL;
import util.Pair;


public class AnimationController {
	private List<Animation> animations;
	
	public AnimationController() {
		animations = new ArrayList<>();
	}
	
	public void addAnimation(Pair<Integer, Integer> position, Animation animation) {
		TinyRL.world.getCurrentRoom().getCell(position).setAnimation(animation);
		animations.add(animation);
	}
	
	public boolean update(double delta) {
		boolean change = !animations.isEmpty();
		List<Animation> finished = new ArrayList<>();
		for(Animation animation: animations) {
			if(animation.done()) {
				Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfAnimation(animation);
				TinyRL.world.getCurrentRoom().getCell(position).setAnimation(null);
				finished.add(animation);
				continue;
			}
			
			if(animation.update(delta)) {
				change = true;
			}
		}
		animations.removeAll(finished);
		return change;
	}
}
