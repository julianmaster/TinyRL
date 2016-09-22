package pattern;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class Engine implements Component {
	
	private static Engine instance = new Engine();
	
	private List<Entity> entities = new ArrayList<>();
	private List<Entity> entitiesToAdd = new ArrayList<>();
	private List<Entity> entitiesToRemove = new ArrayList<>();
	
	private Map<Class<? extends Component>, List<Component>> components = new HashMap<>();
	private ArrayDeque<Event> eventsToProcess = new ArrayDeque<>();
	
	public static boolean DEBUG = false;
	
	private Engine() {
	}
	
	public <E extends Entity> void addEntity(E entity) {
		entitiesToAdd.add(entity);
	}
	
	public <E extends Entity> void addEntities(List<E> entityList) {
		entitiesToAdd.addAll(entityList);
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
	
	public void addHeadEvent(Event event) {
		eventsToProcess.addFirst(event);
	}
	
	public void addTailEvent(Event event) {
		eventsToProcess.addLast(event);
	}
	
	public void removeEntity(Entity entity) {
		entitiesToRemove.add(entity);
	}
	
	public void removeEntitiesByComponentClass(Class<? extends Component> component) {
		List<Entity> entitiesOfComponentClass = new ArrayList<>();
		for(Entity entity : entities) {
			for(Component c : entity) {
				if(component.isAssignableFrom(c.getClass())) {
					entitiesOfComponentClass.add(entity);
				}
			}
		}
		entitiesToRemove.addAll(entitiesOfComponentClass);
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
				if(component.isAssignableFrom(c.getClass())) {
					entitiesOfComponentClass.add(entity);
				}
			}
		}
		return entitiesOfComponentClass;
	}
	
	@Override
	public void process(Event event, double deltaTime) {
		updateListsEngine();
		
		while(!eventsToProcess.isEmpty()) {
			Event nextEvent = eventsToProcess.pollFirst();
			
			if(nextEvent instanceof EntityComponentEvent) {
				EntityComponentEvent nextEntityComponentEvent = (EntityComponentEvent)nextEvent;
				
				for(Component c : nextEntityComponentEvent.getEntity()) {
					if(nextEntityComponentEvent.getComponent().isInstance(c)) {
						c.process(nextEntityComponentEvent, deltaTime);
					}
				}
			}
			else if(nextEvent instanceof ComponentEvent) {
				ComponentEvent nextComponentEvent = (ComponentEvent)nextEvent;
				
				for(Entry<Class<? extends Component>, List<Component>> entry : components.entrySet()) {
					if(entry.getKey().isAssignableFrom(nextComponentEvent.getComponent())) {
						for(Component c : entry.getValue()) {
							c.process(nextComponentEvent, deltaTime);
						}
					}
				}
			}
		}
		
		updateListsEngine();
	}
	
	private void updateListsEngine() {
		for(Entity entity : entitiesToRemove) {
			for(Component component : entity) {
				if(components.containsKey(component.getClass())) {
					components.get(component.getClass()).remove(component);
				}
			}
		}
		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();
		
		for(Entity entity : entitiesToAdd) {
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
		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();
	}
	
	public static Engine getInstance() {
		return instance;
	}
}
