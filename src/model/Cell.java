package model;

import java.util.ArrayList;
import java.util.List;

import model.animations.Animation;
import model.items.Item;
import pattern.Entity;

public class Cell {
	private List<Animation> animations = new ArrayList<>();
	private Entity entity;
	private Item item;
	private Ground ground;

	public Cell(Entity entity, Item item, Ground ground) {
		this.entity = entity;
		this.item = item;
		this.ground = ground;
	}

	public List<Animation> getAnimations() {
		return animations;
	}

	public void setAnimations(List<Animation> animations) {
		this.animations = animations;
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
