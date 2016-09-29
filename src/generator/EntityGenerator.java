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
import model.entities.Door;
import model.entities.EntityTileComponent;
import model.entities.ModelEntity;
import model.entities.Player;
import model.entities.Wall;
import model.turns.PlayerTurnComponent;
import model.turns.TurnControllerComponent;
import model.turns.actions.ChangeRoomActionComponent;
import model.turns.actions.MoveActionComponent;
import model.turns.actions.OpenActionComponent;
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
	
	public static ModelEntity newPlayer(Pair<Integer, Integer> position) {
		Player player = new Player();
		
		player.add(new PositionComponent(position));
		player.add(new EntityTileComponent(Tile.PLAYER));
		player.add(new PlayerTurnComponent());
		player.add(new MoveActionComponent());
		
		return player;
	}
	
	public static Door newDoor(Pair<Integer, Integer> position, Pair<Integer, Integer> nextRoom, Direction direction) {
		Door door = new Door();
		
		door.add(new PositionComponent(position));
		door.add(new EntityTileComponent(Tile.CLOSE_DOOR));
		door.add(new OpenActionComponent());
		door.add(new ChangeRoomActionComponent(nextRoom, direction));
		
		return door;
	}
	
	public static Wall newWall() {
		Wall wall = new Wall();
		
		wall.add(new EntityTileComponent(Tile.WALL));
		
		return wall;
	}
}
