package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Engine implements Component {
	
	private static Engine instance = new Engine();
	
	private List<Entity> entities = new ArrayList<>();
	private Map<Class<? extends Component>, List<Component>> components = new HashMap<>();
	private Stack<Event> eventsToProcess = new Stack<>();
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
		entities.add(entity);
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
	
	public void processEvent(Event event) {
		eventsToProcess.add(event);
	}
	
	public void removeEntity(Entity entity) {
		for(Component component : entity) {
			if(components.containsKey(component.getClass())) {
				components.get(component.getClass()).remove(component);
			}
		}
		entities.remove(entity);
	}
	
	public Entity getEntityByComponent(Component component) {
		for(Entity entity : entities) {
			for(Component c : entity) {
				if(c == component) {
					return entity;
				}
			}
		}
		return null;
	}
	
	public List<Entity> getEntityByComponentClass(Class<? extends Component> component) {
		List<Entity> entitiesOfComponentClass = new ArrayList<>();
		for(Entity entity : entities) {
			for(Component c : entity) {
				if(c.getClass() == component) {
					entitiesOfComponentClass.add(entity);
				}
			}
		}
		return entitiesOfComponentClass;
	}
	
	@Override
	public void process(Event event, double deltaTime) {
		if(start != null) {
			start.process(event, deltaTime);
		}
		
		while(!eventsToProcess.isEmpty()) {
			Event nextEvent = eventsToProcess.pop();
			
			if(nextEvent instanceof EntityComponentEvent) {
				EntityComponentEvent nextEntityComponentEvent = (EntityComponentEvent)nextEvent;
				
				for(Component component : nextEntityComponentEvent.getEntity()) {
					if(component.getClass() == nextEntityComponentEvent.getComponent()) {
						component.process(nextEntityComponentEvent, deltaTime);
					}
				}
			}
			else if(nextEvent instanceof ComponentEvent) {
				ComponentEvent nextComponentEvent = (ComponentEvent)nextEvent;
				
				if(components.containsKey(nextComponentEvent.getComponent())) {
					for(Component c : components.get(nextComponentEvent.getComponent())) {
						c.process(nextComponentEvent, deltaTime);
					}
				}
			}
		}
	}
	
	public static Engine getInstance() {
		return instance;
	}
}
