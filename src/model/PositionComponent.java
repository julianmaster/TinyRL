package model;

import pattern.Component;
import pattern.Event;
import util.Pair;


public class PositionComponent implements Component {
	
	private int x = 0;
	private int y = 0;
	
	public PositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public PositionComponent(Pair<Integer, Integer> position) {
		this.x = position.key;
		this.y = position.value;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangePositionEvent) {
			ChangePositionEvent changePositionEvent = (ChangePositionEvent)e;
			this.x = changePositionEvent.getX();
			this.y = changePositionEvent.getY();
		}
	}
	
	public Pair<Integer, Integer> getPosition() {
		return new Pair<Integer, Integer>(x, y);
	}
}
