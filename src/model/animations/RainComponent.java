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
	private int state = 1;
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
			Animation rain = (Animation)Engine.getInstance().getEntityByComponent(this);
			PositionComponent positionComponent = rain.getComponentByClass(PositionComponent.class);
			AnimationTileComponent animationTileComponent = rain.getComponentByClass(AnimationTileComponent.class);
			
			if(life == 0 && animationTileComponent.getAnimationTile() == AnimationTile.RAIN5) {
				TinyRL.getInstance().getWorld().getCurrentRoom().getCell(positionComponent.getPosition()).getAnimations().remove(rain);
				Engine.getInstance().removeEntity(rain);
				return;
			}
			
			elapseTime = 0;
			if(life > 0) {
				World world = TinyRL.getInstance().getWorld();
				
				world.getCurrentRoom().getCell(positionComponent.getPosition()).getAnimations().remove(rain);
				positionComponent.getPosition().value++;
				world.getCurrentRoom().getCell(positionComponent.getPosition()).getAnimations().add(rain);
				life--;
				
				if(life == 0) {
					Engine.getInstance().processEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN2));
				}
			}
			else {
				if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN4) {
					Engine.getInstance().processEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN5));
				}
				if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN3) {
					Engine.getInstance().processEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN4));
				}
				if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN2) {
					Engine.getInstance().processEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN3));
				}
			}
		}
	}
}
