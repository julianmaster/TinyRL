package model;

public class Energy {
	private int energy = 0;
	private int basicEnergyNeedToAction = 100;
	private int reduceEnergyNeedToAction = 0;
	
	public Energy() {
	}
	
	public Energy(int energyNeedToAction) {
		this.basicEnergyNeedToAction = energyNeedToAction;
	}
	
	public Energy(int basicEnergyNeedToAction, int reduceEnergyNeedToAction) {
		super();
		this.basicEnergyNeedToAction = basicEnergyNeedToAction;
		this.reduceEnergyNeedToAction = reduceEnergyNeedToAction;
	}

	public boolean tick() {
		energy++;
		return energy >= basicEnergyNeedToAction - reduceEnergyNeedToAction;
	}
	
	public void spend() {
		energy -= basicEnergyNeedToAction - reduceEnergyNeedToAction;
	}
	
	public int getReduceEnergyNeedToAction() {
		return reduceEnergyNeedToAction;
	}
	
	public void setReduceEnergyNeedToAction(int reduceEnergyNeedToAction) {
		this.reduceEnergyNeedToAction = reduceEnergyNeedToAction;
	}
}
