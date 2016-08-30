package model.entities;

import model.Energy;
import model.Tile;
import model.turns.TurnHandler;

public class Creature extends Entity {
	
	public float hpPoint;
	public float hpBasicPoint;
	public float hpBasicRegenRate;
	
	public float mana;
	public float manaBasicRegenRate;
	
	public int basicArmor;
	public int basicBlockChance;
	
	public float strength; // hpMaxPoint = strength * 25 + hpBasicPoint, hpMaxRegenRate = strength * 0.1 + hpBasicRegenRate
	public float agility; // maxArmor = agility * 0.3 + basicArmor, blockChance = agility * 1.5 + basicBlockChance
	public float intelligence; // manaMaxPoint = intelligence * 15, manaMaxRegenRate = intelligence * 0.06 + manaBasicRegenRate
	
	public int minBasicDamage;
	public int maxBasicDamage;
	
	public Weapon weapon;
	public Item[] artifact = new Item[5];
	

	public Creature(Tile tile, TurnHandler turnHandler, Energy energy) {
		super(tile, turnHandler, energy);
	}

}
