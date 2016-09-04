package pattern;

public abstract class ComponentEvent implements Event {
	protected Class<? extends Component> component;

	public ComponentEvent(Class<? extends Component> component) {
		this.component = component;
	}
	
	public Class<? extends Component> getComponent() {
		return component;
	}
}
