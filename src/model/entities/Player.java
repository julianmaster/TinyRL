package model.entities;

import model.Energy;

public class Player extends Creature {
	
	public Player() {
		super(new Energy(60));
		
		this.hp = 100f;
		this.hpBasic = 50f;
		this.hpBasicRegenRate = 1.0f;
		
		this.manaBasicRegenRate = 1.0f;
		
		this.basicArmor = 5;
		
		this.strength = 5;
		this.agility = 5;
		this.intelligence = 5;
	}
}
