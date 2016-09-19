package model.animations.rain;

import main.TinyRL;
import model.PositionComponent;
import model.World;
import model.animations.Animation;
import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import model.animations.AnimationTileEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;

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
				if(rain == null) {
					System.out.println(this);
					Engine engine = Engine.getInstance();
					System.out.println("Error - "+this.getClass().getName());
				}
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
