package model;

import java.util.ArrayList;
import java.util.List;

import model.animations.Animation;
import model.items.Item;
import pattern.Entity;

public class Cell {
	private List<Animation> animations = new ArrayList<>();
	private Entity entity;
	private List<Item> items;
	private Ground ground;

	public Cell(Entity entity, List<Item> items, Ground ground) {
		this.entity = entity;
		this.items = items;
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

	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}
}
