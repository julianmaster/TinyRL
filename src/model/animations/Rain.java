package model.animations;

import main.TinyRL;
import util.Pair;

public class Rain extends Animation {
	
	private final static int FPS = 8;
	private double elapseTime = 0;
	
	private int life;
	private boolean finished = false;
	
	public Rain(AnimationTile tile, int life) {
		super(tile);
		this.life = life;
	}

	@Override
	public boolean update(double delta) {
		elapseTime += delta;
		
		if(elapseTime >= 60 / FPS) {
			if(life == 0) {
				finished = true;
				return false;
			}
			
			elapseTime = 0;
			Pair<Integer, Integer> position = TinyRL.world.getCurrentRoom().getPositionOfAnimation(this);
			TinyRL.world.getCurrentRoom().getCell(position).setAnimation(null);
			position.value++;
			
			life--;
			if(life == 0) {
				this.tile = AnimationTile.RAIN2;
			}
			
			TinyRL.world.getCurrentRoom().getCell(position).setAnimation(this);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean done() {
		return finished;
	}
}
