package model;

import pattern.Component;
import pattern.Event;

public class ItemComponent implements Component {
	
	private int extraPhysicalDamage;
	private int extraMagicalDamage;
	
	public ItemComponent(int extraPhysicalDamage, int extraMagicalDamage) {
		super();
		this.extraPhysicalDamage = extraPhysicalDamage;
		this.extraMagicalDamage = extraMagicalDamage;
	}

	@Override
	public void process(Event e, double deltaTime) {
		
	}
	
	public int getExtraPhysicalDamage() {
		return extraPhysicalDamage;
	}
	
	public int getExtraMagicalDamage() {
		return extraMagicalDamage;
	}
}
