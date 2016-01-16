package model.animations;

import java.util.ArrayList;
import java.util.List;

import main.TinyRL;
import model.Cell;
import model.Room;
import util.Pair;


public class AnimationController {
	
	public AnimationController() {
	}
	
	public boolean update(double delta) {
		Room room = TinyRL.world.getCurrentRoom();
		
		boolean change = false;
		
		// Animation Handlers
		List<AnimationHandler> animationHandlersDone = new ArrayList<>();
		for(AnimationHandler animationHandler : room.getAnimationHandlers()) {
			if(animationHandler.done()) {
				animationHandlersDone.add(animationHandler);
			}
			else {
				if(animationHandler.update(delta)) {
					change = true;
				}
			}
		}
		room.getAnimationHandlers().removeAll(animationHandlersDone);
		
		
		// Animations
		for(int x = 0; x < Room.ROOM_SIZE; x++) {
			for(int y = 0; y < Room.ROOM_SIZE; y++) {
				Cell cell = room.getCell(new Pair<Integer, Integer>(x, y));
				
				if(!cell.getAnimations().isEmpty()) {
					List<Animation> animationsIterator = new ArrayList<>(cell.getAnimations());
					List<Animation> animationsDone = new ArrayList<>();
					for(Animation animation : animationsIterator) {
						if(!animation.done()) {
							if(animation.update(delta)) {
								change = true;
							}
						}
						else {
							animationsDone.add(animation);
							change = true;
						}
					}
					cell.getAnimations().removeAll(animationsDone);
				}
			}
		}
		return change;
	}
}
