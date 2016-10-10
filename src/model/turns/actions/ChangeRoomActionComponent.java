package model.turns.actions;

import main.TinyRL;
import model.ChangeCurrentRoomEvent;
import model.ChangePositionEvent;
import model.Direction;
import model.PositionComponent;
import model.Room;
import model.RoomComponent;
import model.World;
import model.turns.NextTickTurnControllerEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class ChangeRoomActionComponent implements Component {
	
	private Pair<Integer, Integer> nextRoomPosition;
	private Direction direction;
	
	public ChangeRoomActionComponent(Pair<Integer, Integer> nextRoomPosition, Direction direction) {
		super();
		this.nextRoomPosition = nextRoomPosition;
		this.direction = direction;
	}

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ChangeRoomActionEvent) {
			ChangeRoomActionEvent changeRoomActionEvent = (ChangeRoomActionEvent)e;
			Entity player = changeRoomActionEvent.getEntityToMove();
			PositionComponent positionPlayer = player.getComponentByClass(PositionComponent.class);

			Room room = (Room)Engine.getInstance().getEntityByComponentClass(RoomComponent.class);
			room.getCell(positionPlayer.getPosition()).setEntity(null);
			
			World world = TinyRL.getInstance().getWorld();
			world.createRoom(nextRoomPosition);
			Room nextRoom = world.getRoom(nextRoomPosition);
			Engine.getInstance().addHeadEvent(new ChangeCurrentRoomEvent(nextRoomPosition));
			
			Entity door = null;
			switch (direction) {
				case N:
					Pair<Integer, Integer> northPlayerPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, northPlayerPosition));
					nextRoom.getCell(northPlayerPosition).setEntity(player);
					door = nextRoom.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, Room.ROOM_SIZE - 1)).getEntity();
					break;
					
				case S:
					Pair<Integer, Integer> southPlayerPosition = new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 1);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, southPlayerPosition));
					nextRoom.getCell(southPlayerPosition).setEntity(player);
					door = nextRoom.getCell(new Pair<Integer, Integer>((Room.ROOM_SIZE - 1) / 2, 0)).getEntity();
					break;
					
				case E:
					Pair<Integer, Integer> eastPlayerPosition = new Pair<Integer, Integer>(1, (Room.ROOM_SIZE - 1) / 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, eastPlayerPosition));
					nextRoom.getCell(eastPlayerPosition).setEntity(player);
					door = nextRoom.getCell(new Pair<Integer, Integer>(0, (Room.ROOM_SIZE - 1) / 2)).getEntity();
					break;
					
				case W:
					Pair<Integer, Integer> westPlayerPosition = new Pair<Integer, Integer>(Room.ROOM_SIZE - 2, (Room.ROOM_SIZE - 1) / 2);
					Engine.getInstance().addHeadEvent(new ChangePositionEvent(player, westPlayerPosition));
					nextRoom.getCell(westPlayerPosition).setEntity(player);
					door = nextRoom.getCell(new Pair<Integer, Integer>(Room.ROOM_SIZE - 1, (Room.ROOM_SIZE - 1) / 2)).getEntity();
					break;
				
				default:
					break;
			}
			
			Engine.getInstance().addHeadEvent(new NextTickTurnControllerEvent());
			Engine.getInstance().addNextTurnEvent(new OpenActionEvent(door));
		}
	}
}
