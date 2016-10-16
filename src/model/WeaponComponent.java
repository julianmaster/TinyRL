package model;

import pattern.Component;
import pattern.Event;

public class WeaponComponent implements Component {
	
	private int minDamage;
	private int maxDamage;
	
	public WeaponComponent(int minDamage, int maxDamage) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	@Override
	public void process(Event e, double deltaTime) {
	}
	
	public int getMinDamage() {
		return minDamage;
	}
	
	public int getMaxDamage() {
		return maxDamage;
	}
}
