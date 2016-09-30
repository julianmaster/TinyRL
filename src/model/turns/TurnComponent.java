package model.turns;

import pattern.Component;
import pattern.Engine;
import pattern.Event;

public abstract class TurnComponent implements Component {
	
	private int energy = 0;
	private int energyNeedToAction = 100;
	private int reduceEnergyNeedToAction = 0;
	
	public TurnComponent() {
	}
	
	public TurnComponent(int energyNeedToAction) {
		this.energyNeedToAction = energyNeedToAction;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof TickTurnEvent) {
			energy++;
			
			if(energy >= energyNeedToAction - reduceEnergyNeedToAction) {
				energy -= energyNeedToAction - reduceEnergyNeedToAction;
				Engine.getInstance().addHeadEvent(new PerformTurnControllerEvent());
			}
			else {
				Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
			}
		}
	}
}
