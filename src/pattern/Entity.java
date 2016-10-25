package pattern;

import java.util.ArrayList;
import java.util.List;


public class Entity extends ArrayList<Component> {
	
	public <E extends Component> E getComponentByClass(Class<E> componentClass) {
		for(Component component : this) {
			if(componentClass.isAssignableFrom(component.getClass())) {
				return (E) component;
			}
		}
		
		return null;
	}
	
	public <E extends Component> List<E> getComponentsByClass(Class<E> componentClass) {
		List<E> components = new ArrayList<>();
		for(Component component : this) {
			if(componentClass.isAssignableFrom(component.getClass())) {
				components.add((E) component);
			}
		}
		
		return components;
	}
	
	public <E extends Component> boolean asComponentOfClass(Class<E> componentClass) {
		for(Component component : this) {
			if(componentClass.isAssignableFrom(component.getClass())) {
				return true;
			}
		}
		
		return false;
	}
}
