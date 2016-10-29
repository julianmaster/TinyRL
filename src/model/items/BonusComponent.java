package model.items;

import pattern.Component;
import pattern.Event;

public class BonusComponent implements Component {
	
	private String name;
	
	public BonusComponent(String name) {
		super();
		this.name = name;
	}

	@Override
	public void process(Event e, double deltaTime) {
		
	}
	
	public String getName() {
		return name;
	}
}
