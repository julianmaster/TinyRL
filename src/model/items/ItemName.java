package model.items;

import java.util.List;

public enum ItemName {
	
	FLUTE("Flute"),
	FIGURINE("Figurine"),
	BELT("Belt"),
	BOOTS("Boots"),
	BRACER("Bracer"),
	CIRCLET("Circlet"),
	CLOAK("Cloak"),
	CROWN("Crown"),
	POUCH("Pouch"),
	GAUNTLETS("Gauntlets"),
	GEM("Gem"),
	GLOVES("Gloves"),
	HELM("Helm"),
	HOOD("Hood"),
	HORN("Horn"),
	RING("Ring"),
	MASK("Mask"),
	MEDALLION("Medallion"),
	NECKLACE("Necklace"),
	ORB("Orb"),
	PENDANT("Pendant"),
	MANTLE("Mantle"),
	ROBE("Robe"),
	SHIELD("Shield"),
	TALISMAN("Talisman"),
	TOTEM("Totem"),
	AMULETTE("Amulette"),
	BOOK("Book"),
	TOME("Tome"),
	MANUEL("Manuel"),
	SCROLL("Scroll"),
	COLLAR("Collar");
	
	public String name;

	private ItemName(String name) {
		this.name = name;
	}
	
	public static ItemName[] getList() {
		return ItemName.values();
	}
}
