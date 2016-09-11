package screens;

import pattern.Entity;

public class ScreenEntities {

	public static Entity createPlayScreen() {
		Entity entity = new Entity();
		
		entity.add(new PlayScreenComponent());
		
		return entity;
	}
}
