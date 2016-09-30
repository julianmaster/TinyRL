package model.entities;

import model.Item;
import model.Weapon;
import pattern.Component;
import pattern.Event;

public class AttributesComponent implements Component {
	protected float hp = 0f;
	protected int hpBasic = 50;
	protected float hpBasicRegenRate = 1.0f;
	
	protected float mana = 0f;
	protected float manaBasicRegenRate = 1.0f;
	
	protected int basicArmor = 5;
	
	protected int strength = 0; // hpMax = strength * 25f + hpBasic, hpRegenRate = strength * 0.05f + hpBasicRegenRate
	protected int agility = 0; // maxArmor = agility * 0.3f + basicArmor, reduceEnergy = agility
	protected int intelligence = 0; // manaMax = intelligence * 15f, manaRegenRate = intelligence * 0.05f + manaBasicRegenRate
	
	protected int minBasicDamage = 0;
	protected int maxBasicDamage = 0;
	
	protected Weapon weapon;
	protected Item[] artifact = new Item[5];
	
	public AttributesComponent(int strength, int agility, int intelligence) {
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		
		hp = getHpMax();
		mana = getManaMax();
	}
	
	@Override
	public void process(Event e, double deltaTime) {
	}

	public float getHp() {
		return hp;
	}
	
	public float getHpMax() {
		return strength * 25f + hpBasic;
	}
	
	public float getMana() {
		return mana;
	}
	
	public float getManaMax() {
		return intelligence * 15f;
	}
	
	public int getArmor() {
		return (int)(agility * 0.3f + basicArmor);
	}
}
