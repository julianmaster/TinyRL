package model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.items.Item;
import model.items.ItemComponent;
import model.items.WeaponComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class AttributesComponent implements Component {
	public static final int MAX_ITEMS = 5;
	
	private boolean dead = false;
	
	private float hp = 0f;
	private int hpBasic = 50;
	
	private float mana = 0f;
	
	private int basicPhysicalArmor = 1;
	private int basicMagicalArmor = 1;
	
	private int strength = 0; // hpMax = strength * 5f + hpBasic
	private int agility = 0; // reduceEnergy = agility, maxPhysicalArmor = agility * 0.3f + basicPhysicalArmor
	private int intelligence = 0; // manaMax = intelligence * 5f, maxMagicalArmor = intelligence * 0.3f + basicMagicalArmor
	
	private Item basicWeapon = null;
	
	private Item weapon;
	private List<Item> items = new ArrayList<>();
	
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
			int totalDamage = 0;
			for(int damage : takeDamageEvent.getDamages()) {
				totalDamage += damage;
			}
			// Reduce damage with armor
			// TODO Separate physical damage and magical damage with armor correspondent
			totalDamage -= getPhysicalArmor();
			hp -= totalDamage;
			
			if(hp <= 0) {
				dead = true;
				Engine.getInstance().addHeadEvent(new KillEvent(takeDamageEvent.getEntity()));
			}
		}
		else if(e instanceof ResolveTurnEvent) {
			// Resolve effects (potions, bonus, malus,...)
		}
	}

	public float getHp() {
		return hp;
	}
	
	public float getHpMax() {
		return strength * 5f + hpBasic;
	}
	
	public float getMana() {
		return mana;
	}
	
	public float getManaMax() {
		return intelligence * 5f;
	}
	
	public int getPhysicalArmor() {
		int physicalArmor = (int)(agility * 0.3f + basicPhysicalArmor);
		for(Item item : items) {
			ItemComponent itemComponent = item.getComponentByClass(ItemComponent.class);
			physicalArmor += itemComponent.getExtraPhysicalDamage();
		}
		
		return physicalArmor;
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

	public Item getWeapon() {
		if(weapon != null) {
			return weapon;
		}
		else {
			return basicWeapon;
		}
	}
	
	public boolean addItem(Item item) {
		if(items.size() < MAX_ITEMS) {
			return items.add(item);
		}
		else {
			return false;
		}
	}
	
	public List<Item> getAllItems() {
		return new ArrayList<>(items);
	}
	
	public void clearItems() {
		items.clear();
	}
}
