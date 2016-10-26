package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.WeaponComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class AttributesComponent implements Component {
	private boolean dead = false;
	
	private float hp = 0f;
	private int hpBasic = 50;
	private float hpBasicRegenRate = 1.0f;
	
	private float mana = 0f;
	private float manaBasicRegenRate = 1.0f;
	
	private int basicPhysicalArmor = 5;
	private int basicMagicalArmor = 5;
	
	private int strength = 0; // hpMax = strength * 25f + hpBasic, hpRegenRate = strength * 0.05f + hpBasicRegenRate
	private int agility = 0; // maxPhysicalArmor = agility * 0.3f + basicPhysicalArmor, reduceEnergy = agility
	private int intelligence = 0; // manaMax = intelligence * 15f, manaRegenRate = intelligence * 0.05f + manaBasicRegenRate, maxMagicalArmor = intelligence * 0.3f + basicMagicalArmor
	
	private Item basicWeapon = null;
	
	private Item weapon;
	private Item[] artifact = new Item[5];
	
	public AttributesComponent(int strength, int agility, int intelligence, Item basicWeapon) {
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.basicWeapon = basicWeapon;
		
		hp = getHpMax();
		mana = getManaMax();
	}
	
	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof TakeDamageEvent) {
			TakeDamageEvent takeDamageEvent = (TakeDamageEvent)e;
			for(int damage : takeDamageEvent.getDamages()) {
				hp -= damage;
			}
			if(hp <= 0) {
				dead = true;
				Engine.getInstance().addHeadEvent(new KillEvent(takeDamageEvent.getEntity()));
			}
		}
		else if(e instanceof ResolveTurnEvent) {
			if(!dead) {
				hp += strength * 0.05f + hpBasicRegenRate;
				hp = Math.min(hp, strength * 25f + hpBasic);
			}
		}
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
	
	public int getPhysicalArmor() {
		return (int)(agility * 0.3f + basicPhysicalArmor);
	}
	
	public int getMagicalArmor() {
		return (int)(intelligence * 0.3f + basicMagicalArmor);
	}
	
	public List<Pair<Integer, Integer>> damages() {
		List<Pair<Integer, Integer>> damages = new ArrayList<Pair<Integer,Integer>>();
		
		WeaponComponent weaponComponent = null;
		if(weapon != null) {
			weaponComponent = weapon.getComponentByClass(WeaponComponent.class);
		}
		else {
			weaponComponent = basicWeapon.getComponentByClass(WeaponComponent.class);
		}
		damages.add(new Pair<Integer, Integer>(weaponComponent.getMinDamage(), weaponComponent.getMaxDamage()));
		
		return damages;
	}
}
