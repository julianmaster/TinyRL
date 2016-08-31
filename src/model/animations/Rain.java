package model.animations;

import main.TinyRL;
import model.World;
import util.Pair;

public class Rain extends Animation {
	
	private final static int FPS = 8;
	private double elapseTime = 0;
	private int life;
	private boolean finished = false;
	
	public Rain(int life, int level) {
		super(AnimationTile.RAIN1, level);
		this.life = life;
	}

	@Override
	public boolean update(double delta) {
		elapseTime += delta;
		if(elapseTime >= 60 / FPS) {
			if(life == 0 && tile == AnimationTile.RAIN5) {
				finished = true;
				return false;
			}
			
			elapseTime = 0;
			if(life > 0) {
				World world = TinyRL.getInstance().getWorld();
				Pair<Integer, Integer> position = world.getCurrentRoom().getPositionOfAnimation(this);
				world.getCurrentRoom().getCell(position).getAnimations().remove(this);
				position.value++;
				world.getCurrentRoom().getCell(position).getAnimations().add(this);
				life--;
				
				if(life == 0) {
					tile = AnimationTile.RAIN2;
				}
			}
			else {
				if(tile == AnimationTile.RAIN4) {
					tile = AnimationTile.RAIN5;
				}
				if(tile == AnimationTile.RAIN3) {
					tile = AnimationTile.RAIN4;
				}
				if(tile == AnimationTile.RAIN2) {
					tile = AnimationTile.RAIN3;
				}
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
