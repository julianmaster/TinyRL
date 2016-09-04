package pattern;

import java.util.ArrayList;


public class Entity extends ArrayList<Component> {
	
	public <E extends Component> E getComponentByClass(Class<E> componentClass) {
		for(Component component : this) {
			if(component.getClass() == componentClass) {
				return (E) component;
			}
		}
		
		return null;
	}
}
