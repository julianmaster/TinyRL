package model.animations;

import pattern.Component;
import pattern.Event;
import util.Pair;


public class PositionComponent implements Component {
	
	private Pair<Integer, Integer> position;
	
	public PositionComponent() {
	}

	public PositionComponent(Pair<Integer, Integer> position) {
		this.position = position;
	}

	public Pair<Integer, Integer> getPosition() {
		return position;
	}

	@Override
	public void process(Event e, double deltaTime) {
	}
}
