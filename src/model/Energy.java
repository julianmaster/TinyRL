package model;

public class Energy {
	private int energy = 0;
	private int energyNeedToAction = 100;
	
	public Energy() {
	}
	
	public Energy(int energyNeedToAction) {
		this.energyNeedToAction = energyNeedToAction;
	}

	public boolean tick() {
		energy++;
		return energy >= energyNeedToAction;
	}
	
	public void spend() {
		energy -= energyNeedToAction;
	}
}
