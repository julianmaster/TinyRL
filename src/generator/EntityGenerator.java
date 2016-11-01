package generator;

import java.util.Random;

import model.Direction;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.World;
import model.WorldComponent;
import model.WorldTickComponent;
import model.animations.Animation;
import model.animations.AnimationLevelComponent;
import model.animations.AnimationTile;
import model.animations.attack.AttackAnimationComponent;
import model.animations.attack.AttackAnimationHandlerComponent;
import model.animations.attack.AttackAnimationTileComponent;
import model.animations.rain.RainAnimationTileComponent;
import model.animations.rain.RainComponent;
import model.animations.rain.RainHandlerComponent;
import model.animations.rain.RainHandlerComponent.RainType;
import model.entities.AttributesComponent;
import model.entities.DoorComponent;
import model.entities.EntityTile;
import model.entities.EntityTileComponent;
import model.entities.dead.EnemyDeadComponent;
import model.entities.dead.PlayerDeadComponent;
import model.entities.dead.SkeletonDeadComponent;
import model.items.Item;
import model.items.ItemComponent;
import model.items.ItemName;
import model.items.ItemRarity;
import model.items.ItemRarityComponent;
import model.items.ItemTile;
import model.items.ItemTileComponent;
import model.items.NameComponent;
import model.items.WeaponComponent;
import model.turns.TurnControllerComponent;
import model.turns.actions.AttackActionComponent;
import model.turns.actions.ChangeRoomActionComponent;
import model.turns.actions.MoveActionComponent;
import model.turns.actions.OpenActionComponent;
import model.turns.entities.PlayerTurnComponent;
import model.turns.entities.SkeletonTurnComponent;
import pattern.Entity;
import screens.PlayScreenComponent;
import util.Pair;

public class EntityGenerator {
	
	private static Random rand = new Random();

	
	
	/**
	 * World 
	 */
	
	public static World newWorld() {
		World world = new World();
		
		world.add(new WorldComponent());
		world.add(new WorldTickComponent());
		world.add(new AttackAnimationHandlerComponent());
		
		return world;
	}
	
	
	
	
	
	/**
	 * Screens
	 */
	
	public static Entity newPlayScreen() {
		Entity entity = new Entity();
		
		entity.add(new PlayScreenComponent());
		
		return entity;
	}
	
	
	
	
	
		
	
	/**
	 * Room
	 */
	
	public static Room newRoom(Pair<Integer, Integer> position) {
		Room room = new Room(position);
		
		room.add(new RoomComponent());
		
		return room;
	}
	
	
	
	
		
	
	/**
	 * Turn Controller
	 */
	
	public static Entity newTurnController() {
		Entity turnController = new Entity();
		
		turnController.add(new TurnControllerComponent());
		
		return turnController;
	}
	
	
		
	
	
	
	
	/**
	 * Animations Handlers
	 */
	
	public static Entity newRainHandler(RainType rainType) {
		Entity rainHandler = new Entity();
		
		rainHandler.add(new RainHandlerComponent(rainType));

		return rainHandler;
	}
	
	
	
	
	
	
	/**
	 * Animations
	 */
	
	public static Animation newRain(Pair<Integer, Integer> position) {
		Animation rain = new Animation();
		
		int rainLife = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
		rain.add(new RainComponent(rainLife));
		rain.add(new PositionComponent(position));
		rain.add(new RainAnimationTileComponent());
		rain.add(new AnimationLevelComponent(rainLife));
		
		return rain;
	}
	
	public static Animation newAttackAnimation(Pair<Integer, Integer> position, char tile) {
		Animation attackAnimation = new Animation();
		
		attackAnimation.add(new AttackAnimationTileComponent(AnimationTile.ATTACK, tile));
		attackAnimation.add(new AttackAnimationComponent());
		attackAnimation.add(new PositionComponent(position));
		attackAnimation.add(new AnimationLevelComponent(0));
		
		return attackAnimation;
	}
	
	
	
	
	
	
	/**
	 * Entities
	 */
	
	public static Entity newPlayer(Pair<Integer, Integer> position) {
		Entity player = new Entity();
		
		player.add(new PositionComponent(position));
		player.add(new AttributesComponent(5, 5, 5, EntityGenerator.newBasicPlayerWeapon()));
		player.add(new EntityTileComponent(EntityTile.PLAYER));
		player.add(new PlayerTurnComponent(60));
		player.add(new MoveActionComponent());
		player.add(new AttackActionComponent());
		player.add(new PlayerDeadComponent());
		
		return player;
	}
	
	public static Entity newDoor(Pair<Integer, Integer> position, Pair<Integer, Integer> nextRoom, Direction direction) {
		Entity door = new Entity();
		
		door.add(new DoorComponent());
		door.add(new PositionComponent(position));
		door.add(new EntityTileComponent(EntityTile.CLOSE_DOOR));
		door.add(new OpenActionComponent());
		door.add(new ChangeRoomActionComponent(nextRoom, direction));
		
		return door;
	}
	
	public static Entity newWall() {
		Entity wall = new Entity();
		
		wall.add(new EntityTileComponent(EntityTile.WALL));
		
		return wall;
	}
	
	public static Entity newTree(EntityTile entityTile) {
		Entity tree = new Entity();
		
		tree.add(new EntityTileComponent(entityTile));
		
		return tree;
	}
	
	public static Entity newRandomTree() {
		Entity tree = new Entity();
		
		EntityTile tile = null;
		switch (rand.nextInt(3)) {
			case 0:
			tile = EntityTile.TREE1;
			break;
			
			case 1:
			tile = EntityTile.TREE2;
			break;
			
			case 2:
			tile = EntityTile.TREE3;
			break;
		}
		
		tree.add(new EntityTileComponent(tile));
		
		return tree;
	}
	
	public static Entity newSkeleton(Pair<Integer, Integer> position) {
		Entity skeleton = new Entity();
		
		skeleton.add(new PositionComponent(position));
		skeleton.add(new EntityTileComponent(EntityTile.SKELETON));
		skeleton.add(new AttributesComponent(3, 3, 3, EntityGenerator.newBasicSkeletonWeapon()));
		skeleton.add(new SkeletonTurnComponent(100));
		skeleton.add(new MoveActionComponent());
		skeleton.add(new AttackActionComponent());
		skeleton.add(new SkeletonDeadComponent());
		
		if(rand.nextInt() < 0.3) {
			skeleton.getComponentByClass(AttributesComponent.class).addItem(EntityGenerator.newRandomWhiteItem());
			
			if(rand.nextInt() < 0.1) {
				skeleton.getComponentByClass(AttributesComponent.class).addItem(EntityGenerator.newRandomWhiteItem());
			}
		}
		
		return skeleton;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Items
	 */
	
	public static Item newGrayItem(String name, ItemTile itemTile) {
		Item item = new Item();
		
		item.add(new NameComponent(name));
		item.add(new ItemTileComponent(itemTile));
		item.add(new ItemRarityComponent(ItemRarity.USELESS_ITEM));
		
		return item;
	}
	
	
	public static Item newRandomWhiteItem() {
		Item item = new Item();
		
		ItemName itemName = ItemName.getList()[rand.nextInt(ItemName.getList().length)];
		item.add(new NameComponent(itemName.name));
		
		int extraPhysicalDamage = 0;
		int extraMagicalDamage = 0;
		if(rand.nextBoolean()) {
			extraPhysicalDamage++;
		}
		else {
			extraMagicalDamage++;
		}
		
		item.add(new ItemComponent(extraPhysicalDamage, extraMagicalDamage));
		item.add(new ItemTileComponent(ItemTile.COMMUN_ITEM));
		item.add(new ItemRarityComponent(ItemRarity.COMMUN_ITEM));
		
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Weapons
	 */
	
	public static Item newBasicPlayerWeapon() {
		Item weapon = new Item();
		
		weapon.add(new WeaponComponent(5, 10));
		
		return weapon;
	}
	
	public static Item newBasicSkeletonWeapon() {
		Item weapon = new Item();
		
		weapon.add(new WeaponComponent(4, 6));
		
		return weapon;
	}
	
	public static final float MAGIC_ITEM_DROP = 0.3f;
	public static final float RARE_ITEM_DROP = 0.05f;
	public static final float CHARGED_ITEM_DROP = 0.05f;
	
	public static Item newRandomWeapon() {
		Item item = new Item();

		// White item
		int extra = 0;
		
		// Rare item
		if(rand.nextFloat() < 0.05) {
			
		}
		
		if(rand.nextFloat() < 0.2) {
//			item.add(new WeaponComponent(minDamage, maxDamage))
		}
		
		return item;
	}
}
