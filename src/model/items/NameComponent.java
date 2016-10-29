package model.items;

import java.util.List;

import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;

public class NameComponent implements Component {
	
	private String objectName;

	public NameComponent(String objectName) {
		super();
		this.objectName = objectName;
	}

	@Override
	public void process(Event e, double deltaTime) {
		
	}
	
	public String getFullName() {
		Entity object = Engine.getInstance().getEntityByComponent(this);
		List<BonusComponent> bonusComponentList = object.getComponentsByClass(BonusComponent.class);
		
		StringBuilder name = new StringBuilder();
		name.append("a ");
		for(BonusComponent bonusComponent : bonusComponentList) {
			name.append(bonusComponent.getName()+" ");
		}
		name.append(objectName);
		
		return name.toString();
	}
}
