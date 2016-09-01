package model.entities;

import model.Energy;
import model.Item;
import model.Tile;
import model.Weapon;
import model.turns.TurnHandler;

public class Creature extends Entity {
	
	protected float hp = 0;
	protected float hpBasic = 0;
	protected float hpBasicRegenRate = 0;
	
	protected float mana = 0;
	protected float manaBasicRegenRate = 0;
	
	protected int basicArmor = 0;
	
	protected float strength = 0; // hpMax = strength * 25f + hpBasic, hpRegenRate = strength * 0.05f + hpBasicRegenRate
	protected float agility = 0; // maxArmor = agility * 0.3f + basicArmor, reduceEnergy = agility
	protected float intelligence = 0; // manaMax = intelligence * 15f, manaRegenRate = intelligence * 0.05f + manaBasicRegenRate
	
	protected int minBasicDamage = 0;
	protected int maxBasicDamage = 0;
	
	protected Weapon weapon;
	protected Item[] artifact = new Item[5];
	
	public Creature(Tile tile, TurnHandler turnHandler, Energy energy) {
		super(tile, turnHandler, energy);
	}
	
	public float getHp() {
		return hp;
	}
	
	public void setHp(float hp) {
		this.hp = hp;
	}
	
	public float getHpMax() {
		return strength * 25f + hpBasic;
	}
	
	public float getMana() {
		return mana;
	}
	
	public void setMana(float mana) {
		this.mana = mana;
	}
	
	public float getManaMax() {
		return intelligence * 15f;
	}
	
	public int getArmor() {
		return (int)(agility * 0.3f + basicArmor);
	}
}
