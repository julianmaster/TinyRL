package model.animations.rain;

import main.TinyRL;
import model.ChangePositionEvent;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.World;
import model.animations.Animation;
import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import model.animations.AnimationTileEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class RainComponent implements Component {
	
	private final static int FPS = 8;
	private double elapseTime = 0;
	private int life;
	private int state = 1;
	
	public RainComponent(int life) {
		this.life = life;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RainEvent) {
			elapseTime += deltaTime;
			if(elapseTime >= 60 / FPS) {
				Animation rain = (Animation)Engine.getInstance().getEntityByComponent(this);
				PositionComponent positionComponent = rain.getComponentByClass(PositionComponent.class);
				AnimationTileComponent animationTileComponent = rain.getComponentByClass(AnimationTileComponent.class);
				
				Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
				
				if(life == 0 && animationTileComponent.getAnimationTile() == AnimationTile.RAIN5) {
					room.getCell(positionComponent.getPosition()).getAnimations().remove(rain);
					Engine.getInstance().removeEntity(rain);
					return;
				}
				
				elapseTime = 0;
				if(life > 0) {
					Pair<Integer, Integer> position = positionComponent.getPosition();
					room.getCell(positionComponent.getPosition()).getAnimations().remove(rain);
					position.value++;
					room.getCell(position).getAnimations().add(rain);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(rain, position));
					
					life--;
					
					if(life == 0) {
						Engine.getInstance().addHeadEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN2));
					}
				}
				else {
					if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN4) {
						Engine.getInstance().addHeadEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN5));
					}
					if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN3) {
						Engine.getInstance().addHeadEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN4));
					}
					if(animationTileComponent.getAnimationTile() == AnimationTile.RAIN2) {
						Engine.getInstance().addHeadEvent(new AnimationTileEvent(Engine.getInstance().getEntityByComponent(this), AnimationTile.RAIN3));
					}
				}
			}
		}
	}
}
