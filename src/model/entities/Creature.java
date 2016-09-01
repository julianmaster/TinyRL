package model.entities;

import model.Energy;
import model.Item;
import model.Tile;
import model.Weapon;
import model.turns.TurnHandler;

public class Creature extends Entity {
	
	private float hpPoint = 0;
	private float hpBasicPoint = 0;
	private float hpBasicRegenRate = 0;
	
	private float mana = 0;
	private float manaBasicRegenRate = 0;
	
	private int basicArmor = 0;
	
	private float strength = 0; // hpMaxPoint = strength * 25 + hpBasicPoint, hpMaxRegenRate = strength * 0.05 + hpBasicRegenRate
	private float agility = 0; // maxArmor = agility * 0.3 + basicArmor, reduceEnergy = agility
	private float intelligence = 0; // manaMaxPoint = intelligence * 15, manaMaxRegenRate = intelligence * 0.05 + manaBasicRegenRate
	
	private int minBasicDamage = 0;
	private int maxBasicDamage = 0;
	
	private Weapon weapon;
	private Item[] artifact = new Item[5];
	
	public Creature(Tile tile, TurnHandler turnHandler, Energy energy) {
		super(tile, turnHandler, energy);
	}
	
	public float getHpPoint() {
		return hpPoint;
	}
}
