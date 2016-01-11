package model.animations;


public abstract class Animation {
	protected AnimationTile tile;

	public Animation(AnimationTile tile) {
		this.tile = tile;
	}
	
	public abstract boolean update(double delta);
	
	public abstract boolean done();
	
	public AnimationTile getTile() {
		return tile;
	}
}
