package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine implements Component {
	
	private static Engine instance = new Engine();
	
	private Map<Class<? extends Component>, List<Component>> components = new HashMap<>();
	private Component start = null;
	
	private Engine() {
	}
	
	public void addEntity(Entity entity) {
		for(Component component : entity) {
			if(components.containsKey(component.getClass())) {
				components.get(component.getClass()).add(component);
			}
			else {
				ArrayList<Component> listComponents = new ArrayList<>();
				listComponents.add(component);
				components.put(component.getClass(), listComponents);
			}
		}
	}
	
	public void updateEntity(Entity entity) {
		for(Component component : entity) {
			if(components.containsKey(component.getClass())) {
				if(!components.get(component.getClass()).contains(component)) {
					components.get(component.getClass()).add(component);
				}
			}
			else {
				ArrayList<Component> listComponents = new ArrayList<>();
				listComponents.add(component);
				components.put(component.getClass(), listComponents);
			}
		}
	}
	
	public void processEvent(Class<? extends Component> component, Event event, double deltaTime) {
		if(components.containsKey(component)) {
			for(Component c : components.get(component)) {
				c.process(event, deltaTime);
			}
		}
	}
	
	public void removeEntity(Entity entity) {
		for(Component component : entity) {
			if(components.containsKey(component.getClass())) {
				components.get(component.getClass()).remove(component);
			}
		}
	}
	
	@Override
	public void process(Event event, double deltaTime) {
		if(start != null) {
			start.process(event, deltaTime);
		}
	}
	
	public static Engine getInstance() {
		return instance;
	}
}
