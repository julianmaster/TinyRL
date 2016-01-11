package model.animations;

import main.TinyRL;
import util.Pair;

public class Rain extends Animation {
	
	private final static int FPS = 10;
	private double elapseTime = 0;
	
	private Pair<Integer, Integer> position;
	private int target;
	private boolean finished = false;
	
	public Rain(AnimationTile tile, Pair<Integer, Integer> position, int target) {
		super(tile);
		this.position = position;
		this.target = target;
	}

	@Override
	public boolean update(double delta) {
		elapseTime += delta;
		
		if(elapseTime >= 60 / FPS) {
			if(position.value.equals(target)) {
				finished = true;
				return false;
			}
			
			position.value++;
			if(position.value.equals(target)) {
				this.tile = AnimationTile.RAIN2;
//				TinyRL.world.getCurrentRoom().getCell(position.key, position.value).setAnimation(AnimationTile.RAIN2);
			}
			else {
				TinyRL.world.getCurrentRoom().getCell(position.key, position.value).setAnimation(AnimationTile.RAIN1);
			}
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean done() {
		return finished;
	}
}
