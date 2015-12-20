package model;

public class Cell {
	private Entity entity;
	private Item item;
	
	public Cell(Entity entity, Item item) {
		this.entity = entity;
		this.item = item;
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
}
