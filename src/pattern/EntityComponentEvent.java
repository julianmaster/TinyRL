package pattern;

public abstract class EntityComponentEvent extends ComponentEvent {
	protected Entity entity;

	public EntityComponentEvent(Class<? extends Component> component, Entity entity) {
		super(component);
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
}
