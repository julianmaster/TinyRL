package model.items;

public enum ItemTile {
	PILE_OF_BONES('%'),
	COMMUN_ITEM('&');
	
	public char tile;
	
	private ItemTile(char tile) {
		this.tile = tile;
	}
}
