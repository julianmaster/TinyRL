package model.items;

import pattern.Component;
import pattern.Event;

public class ItemComponent implements Component {
	
	private int regenerateHp;
	private int extraPhysicalDamage;
	private int extraMagicalDamage;
	
	public ItemComponent(int regenerateHp, int extraPhysicalDamage, int extraMagicalDamage) {
		this.regenerateHp = regenerateHp;
		this.extraPhysicalDamage = extraPhysicalDamage;
		this.extraMagicalDamage = extraMagicalDamage;
	}

	@Override
	public void process(Event e, double deltaTime) {
		
	}

	public int getRegenerateHp() {
		return regenerateHp;
	}

	public int getExtraPhysicalDamage() {
		return extraPhysicalDamage;
	}

	public int getExtraMagicalDamage() {
		return extraMagicalDamage;
	}
}
