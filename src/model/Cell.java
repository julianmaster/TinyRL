package model;

import java.util.ArrayList;
import java.util.List;

import model.animations.Animation;
import model.entities.ModelEntity;

public class Cell {
	private List<Animation> animations = new ArrayList<>();
	private ModelEntity entity;
	private Item item;
	private Ground ground;

	public Cell(ModelEntity entity, Item item, Ground ground) {
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

	public ModelEntity getEntity() {
		return entity;
	}

	public void setEntity(ModelEntity entity) {
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
