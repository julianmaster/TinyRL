package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.WeaponComponent;
import pattern.Component;
import pattern.Event;
import util.Pair;

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
	
	protected Item basicWeapon = null;
	
	protected Item weapon;
	protected Item[] artifact = new Item[5];
	
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
			System.out.println("life: "+hp+"/"+getHpMax());
		}
		else if(e instanceof ResolveTurnEvent) {
			hp += strength * 0.05f + hpBasicRegenRate;
			hp = Math.min(hp, strength * 25f + hpBasic);
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
	
	public int getArmor() {
		return (int)(agility * 0.3f + basicArmor);
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
