package model;

import pattern.ComponentEvent;

public class WorldTickEvent extends ComponentEvent {

	public WorldTickEvent() {
		super(WorldTickComponent.class);
	}

}
