package generator;

import java.util.Random;

import model.Direction;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.Tile;
import model.World;
import model.WorldComponent;
import model.WorldTickComponent;
import model.animations.Animation;
import model.animations.AnimationLevelComponent;
import model.animations.rain.RainAnimationTileComponent;
import model.animations.rain.RainComponent;
import model.animations.rain.RainHandlerComponent;
import model.animations.rain.RainHandlerComponent.RainType;
import model.entities.AttributesComponent;
import model.entities.DoorComponent;
import model.entities.EntityTileComponent;
import model.turns.TurnControllerComponent;
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
	
	
	
	/**
	 * Model Entities
	 */
	
	public static Entity newPlayer(Pair<Integer, Integer> position) {
		Entity player = new Entity();
		
		player.add(new PositionComponent(position));
		player.add(new AttributesComponent(5, 5, 5));
		player.add(new EntityTileComponent(Tile.PLAYER));
		player.add(new PlayerTurnComponent(60));
		player.add(new MoveActionComponent());
		
		return player;
	}
	
	public static Entity newDoor(Pair<Integer, Integer> position, Pair<Integer, Integer> nextRoom, Direction direction) {
		Entity door = new Entity();
		
		door.add(new DoorComponent());
		door.add(new PositionComponent(position));
		door.add(new EntityTileComponent(Tile.CLOSE_DOOR));
		door.add(new OpenActionComponent());
		door.add(new ChangeRoomActionComponent(nextRoom, direction));
		
		return door;
	}
	
	public static Entity newWall() {
		Entity wall = new Entity();
		
		wall.add(new EntityTileComponent(Tile.WALL));
		
		return wall;
	}
	
	public static Entity newTree(Tile tile) {
		Entity tree = new Entity();
		
		tree.add(new EntityTileComponent(tile));
		
		return tree;
	}
	
	public static Entity newRandomTree() {
		Entity tree = new Entity();
		
		Tile tile = null;
		switch (rand.nextInt(3)) {
			case 0:
			tile = Tile.TREE1;
			break;
			
			case 1:
			tile = Tile.TREE2;
			break;
			
			case 2:
			tile = Tile.TREE3;
			break;
		}
		
		tree.add(new EntityTileComponent(tile));
		
		return tree;
	}
	
	public static Entity newSkeleton(Pair<Integer, Integer> position) {
		Entity skeleton = new Entity();
		
		skeleton.add(new PositionComponent(position));
		skeleton.add(new EntityTileComponent(Tile.SKELETON));
		skeleton.add(new AttributesComponent(5, 5, 5));
		skeleton.add(new SkeletonTurnComponent(100));
		skeleton.add(new MoveActionComponent());
		
		return skeleton;
	}
}
