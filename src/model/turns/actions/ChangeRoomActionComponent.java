package model.turns.actions;

import main.TinyRL;
import model.ChangePositionEvent;
import model.Direction;
import model.PositionComponent;
import model.Room;
import model.World;
import model.animations.rain.RainComponent;
import model.animations.rain.RainHandlerComponent;
import model.entities.Door;
import model.entities.EntityTileComponent;
import model.entities.ModelEntity;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class ChangeRoomActionComponent implements Component {
	
	private Pair<Integer, Integer> nextRoom;
	private Direction direction;
	
	public ChangeRoomActionComponent(Pair<Integer, Integer> nextRoom, Direction direction) {
		super();
		this.nextRoom = nextRoom;
		this.direction = direction;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeRoomActionEvent) {
			ChangeRoomActionEvent changeRoomActionEvent = (ChangeRoomActionEvent)e;
			ModelEntity player = changeRoomActionEvent.getEntityToMove();
			PositionComponent positionPlayer = player.getComponentByClass(PositionComponent.class);
			
			World world = TinyRL.getInstance().getWorld();
			world.getCurrentRoom().getCell(positionPlayer.getPosition()).setEntity(null);
			Engine.getInstance().removeEntitiesByComponentClass(RainHandlerComponent.class);
			Engine.getInstance().removeEntitiesByComponentClass(RainComponent.class);
			Engine.getInstance().removeEntitiesByComponentClass(EntityTileComponent.class);
			
			world.createRoom(nextRoom);
			Room room = world.getRoom(nextRoom);
			world.loadRoom(nextRoom);
			
			Door door = null;
			switch (direction) {
				case N:
					Pair<Integer, Integer> northPlayerPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, northPlayerPosition));
					room.getCell(northPlayerPosition).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1)).getEntity();
					break;
					
				case S:
					Pair<Integer, Integer> southPlayerPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 1);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, southPlayerPosition));
					room.getCell(southPlayerPosition).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0)).getEntity();
					break;
					
				case E:
					Pair<Integer, Integer> eastPlayerPosition = new Pair<Integer, Integer>(1, (Room.ROOM_SIZE - 1) / 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, eastPlayerPosition));
					room.getCell(eastPlayerPosition).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2)).getEntity();
					break;
					
				case W:
					Pair<Integer, Integer> westPlayerPosition = new Pair<Integer, Integer>(Room.ROOM_SIZE - 2, (Room.ROOM_SIZE - 1) / 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, westPlayerPosition));
					room.getCell(westPlayerPosition).setEntity(player);
					door = (Door)room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2)).getEntity();
					break;
				
				case NE:
					break;
				
				case NW:
					break;
				
				case SE:
					break;
				
				case SW:
					break;
				
				default:
					break;
			}
//			door.open();
			
			Engine.getInstance().addEntities(room.getAnimationHandlers());
			Engine.getInstance().addEntities(room.getAnimations());
			Engine.getInstance().addEntities(room.getModelEntities());
			
			Engine.getInstance().addHeadEvent(new OpenActionEvent(door));
			
			
			
			
			
			
//			World world = TinyRL.getInstance().getWorld();
//			
//			Pair<Integer, Integer> positionPlayer = world.getCurrentRoom().getPositionOfEntity(player);
//			Pair<Integer, Integer> positionTarget = positionPlayer.clone();
//			positionTarget.key += dx;
//			positionTarget.value += dy;
//			
//			ModelEntity entity = world.getCurrentRoom().getCell(positionTarget).getEntity();
//			if(entity instanceof RoomChanger) {
//				RoomChanger roomChanger = (RoomChanger)entity;
//				Pair<Integer, Integer> nextRoom = roomChanger.changeRoom();
//				// Remove player of the current room
//				world.getCurrentRoom().getCell(positionPlayer).setEntity(null);
//				
//				Engine.getInstance().removeEntitiesByComponentClass(RainHandlerComponent.class);
//				Engine.getInstance().removeEntitiesByComponentClass(RainComponent.class);
//				Engine engine = Engine.getInstance();
//				world.createRoom(nextRoom);
//				Room room = world.getRoom(nextRoom);
//				Engine.getInstance().addEntities(room.getAnimationHandlers());
//				Engine.getInstance().addEntities(room.getAnimations());
//				
//				Door door = null;
//				switch (roomChanger.getDirection()) {
//					case N:
//						room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 2)).setEntity(player);
//						door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1)).getEntity();
//						break;
//						
//					case S:
//						room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 1)).setEntity(player);
//						door = (Door)room.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0)).getEntity();
//						break;
//						
//					case E:
//						room.getCell(new Pair<Integer, Integer>(1, (Room.ROOM_SIZE - 1) / 2)).setEntity(player);
//						door = (Door)room.getCell(new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2)).getEntity();
//						break;
//						
//					case W:
//						room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 2, (Room.ROOM_SIZE - 1) / 2)).setEntity(player);
//						door = (Door)room.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2)).getEntity();
//						break;
//					
//					case NE:
//						break;
//					
//					case NW:
//						break;
//					
//					case SE:
//						break;
//					
//					case SW:
//						break;
//					
//					default:
//						break;
//				}
//				door.open();
//				
//				world.loadRoom(nextRoom);
//				Engine.DEBUG = true;
//				return true;
//			}
//			return false;
		}
	}
}
