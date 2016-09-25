package model;

import java.util.Collections;

import main.TinyRL;
import model.animations.Animation;
import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import model.entities.EntityTileComponent;
import model.entities.ModelEntity;
import pattern.Component;
import pattern.Engine;
import pattern.Event;
import ui.AsciiPanel;
import util.Pair;

public class RoomComponent implements Component {

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RenderRoomEvent) {
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			for(int x = 0; x < Room.ROOM_SIZE; x++) {
				for(int y = 0; y < Room.ROOM_SIZE; y++) {
					
					// TODO change this
					Room room = (Room)Engine.getInstance().getEntityByComponent(this);
					Cell cell = room.getCell(new Pair<Integer, Integer>(x, y));
					
					if(!cell.getAnimations().isEmpty()) {
						Collections.sort(cell.getAnimations());
						Animation animation = cell.getAnimations().get(0);
						AnimationTile animationTile = animation.getComponentByClass(AnimationTileComponent.class).getAnimationTile();
						asciiPanel.write(x, y, animationTile.tile, animationTile.color);
					}
					else if(cell.getEntity() != null) {
						ModelEntity modelEntity = cell.getEntity();
						Tile entityTile = modelEntity.getComponentByClass(EntityTileComponent.class).getTile();
						asciiPanel.write(x, y, entityTile.tile, entityTile.color);
					}
					else {
						asciiPanel.write(x, y, cell.getGround().tile, cell.getGround().color);
					}
				}
			}
		}
	}
}
