package model.animations;

import model.Direction;

public interface Animation {
	public int x();
	public int y();
	public Direction direction();
	public boolean done();
	public void update();
}
