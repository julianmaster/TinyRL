package model;

import pattern.Entity;

public class GeneralEntity {
	
	public static Entity newGeneral() {
		Entity general = new Entity();
		
		general.add(new GeneralComponent());
		
		return general;
	}
}
