package model.items;

public enum ItemTile {
	POTIONS('¡'),
	PILE_OF_BONES('%'),
	BASIC_ITEM('&');
	
	public char tile;
	
	private ItemTile(char tile) {
		this.tile = tile;
	}
}
