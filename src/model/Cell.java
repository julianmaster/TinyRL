package model;

import model.animations.Animation;
import model.entities.Entity;

public class Cell {
	private Animation animation;
	private Entity entity;
	private Item item;
	private Ground ground;

	public Cell(Animation animation, Entity entity, Item item, Ground ground) {
		this.animation = animation;
		this.entity = entity;
		this.item = item;
		this.ground = ground;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}
}
