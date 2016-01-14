package model.animations;

public interface AnimationHandler {
	
	public abstract boolean update(double delta);
	
	public abstract boolean done();
}
