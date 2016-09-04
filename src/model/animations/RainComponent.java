package model.animations;

import main.TinyRL;
import model.World;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class RainComponent implements Component {
	
	private final static int FPS = 8;
	private double elapseTime = 0;
	private int life;
//	private boolean finished = false;
//	
//	public Rain(int life, int level) {
//		super(AnimationTile.RAIN1, level);
//		this.life = life;
//	}
//
//	@Override
//	public boolean update(double delta) {
//		elapseTime += delta;
//		if(elapseTime >= 60 / FPS) {
//			if(life == 0 && tile == AnimationTile.RAIN5) {
//				finished = true;
//				return false;
//			}
//			
//			elapseTime = 0;
//			if(life > 0) {
//				World world = TinyRL.getInstance().getWorld();
//				Pair<Integer, Integer> position = world.getCurrentRoom().getPositionOfAnimation(this);
//				world.getCurrentRoom().getCell(position).getAnimations().remove(this);
//				position.value++;
//				world.getCurrentRoom().getCell(position).getAnimations().add(this);
//				life--;
//				
//				if(life == 0) {
//					tile = AnimationTile.RAIN2;
//				}
//			}
//			else {
//				if(tile == AnimationTile.RAIN4) {
//					tile = AnimationTile.RAIN5;
//				}
//				if(tile == AnimationTile.RAIN3) {
//					tile = AnimationTile.RAIN4;
//				}
//				if(tile == AnimationTile.RAIN2) {
//					tile = AnimationTile.RAIN3;
//				}
//			}
//			return true;
//		}
//		
//		return false;
//	}
//	
//	@Override
//	public boolean done() {
//		return finished;
//	}
	
	
	public RainComponent(int life) {
		this.life = life;
	}

	@Override
	public void process(Event e, double deltaTime) {
		elapseTime += deltaTime;
		if(elapseTime >= 60 / FPS) {
			if(life == 0) {
				Engine.getInstance().removeEntity(Engine.getInstance().getEntityByComponent(this));
			}
			
//			Engine.getInstance().processEvent(event, deltaTime)
			
			elapseTime = 0;
			if(life > 0) {
				World world = TinyRL.getInstance().getWorld();
				Animation rain = (Animation)Engine.getInstance().getEntityByComponent(this);
				Pair<Integer, Integer> position = world.getCurrentRoom().getPositionOfAnimation(rain);
				world.getCurrentRoom().getCell(position).getAnimations().remove(rain);
				position.value++;
				world.getCurrentRoom().getCell(position).getAnimations().add(rain);
				life--;
				
				if(life == 0) {
//					tile = AnimationTile.RAIN2;
					Engine.getInstance().processEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this)));
				}
			}
			else {
//				if(tile == AnimationTile.RAIN4) {
//					tile = AnimationTile.RAIN5;
//				}
//				if(tile == AnimationTile.RAIN3) {
//					tile = AnimationTile.RAIN4;
//				}
//				if(tile == AnimationTile.RAIN2) {
//					tile = AnimationTile.RAIN3;
//				}
			}
		}
	}
}
