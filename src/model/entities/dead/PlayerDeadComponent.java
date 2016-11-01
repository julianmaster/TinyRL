package model.entities.dead;

import model.entities.KillEvent;
import pattern.Event;

public class PlayerDeadComponent extends DeadComponent {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof KillEvent) {
			System.out.println("PlayerDeadComponent: player is dead");
		}
	}
}
