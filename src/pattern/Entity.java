package pattern;

import java.util.ArrayList;


public class Entity extends ArrayList<Component> {
	
	public <E extends Component> E getComponentByClass(Class<E> componentClass) {
		for(Component component : this) {
			if(componentClass.isAssignableFrom(component.getClass())) {
				return (E) component;
			}
		}
		
		return null;
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
