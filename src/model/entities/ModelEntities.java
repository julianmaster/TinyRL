package model.entities;

import model.Direction;
import model.PositionComponent;
import model.Tile;
import model.turns.PlayerTurnComponent;
import model.turns.actions.ChangeRoomActionComponent;
import model.turns.actions.MoveActionComponent;
import model.turns.actions.OpenActionComponent;
import util.Pair;

public class ModelEntities {
	
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
