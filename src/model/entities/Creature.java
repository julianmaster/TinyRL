package model.entities;

import model.Energy;
import model.Item;
import model.Tile;
import model.Weapon;
import model.turns.TurnHandler;

public class Creature extends Entity {
	
	public float hpPoint = 0;
	public float hpBasicPoint = 0;
	public float hpBasicRegenRate = 0;
	
	public float mana = 0;
	public float manaBasicRegenRate = 0;
	
	public int basicArmor = 0;
	
	public float strength = 0; // hpMaxPoint = strength * 25 + hpBasicPoint, hpMaxRegenRate = strength * 0.05 + hpBasicRegenRate
	public float agility = 0; // maxArmor = agility * 0.3 + basicArmor, reduceEnergy = agility
	public float intelligence = 0; // manaMaxPoint = intelligence * 15, manaMaxRegenRate = intelligence * 0.05 + manaBasicRegenRate
	
	public int minBasicDamage = 0;
	public int maxBasicDamage = 0;
	
	public Weapon weapon;
	public Item[] artifact = new Item[5];
	
	public Creature(Tile tile, TurnHandler turnHandler, Energy energy) {
		super(tile, turnHandler, energy);
	}
}
