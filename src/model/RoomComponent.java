package model;

import java.util.Collections;
import java.util.List;

import main.TinyRL;
import model.animations.Animation;
import model.animations.AnimationTile;
import model.animations.AnimationTileComponent;
import model.entities.EntityTile;
import model.entities.EntityTileComponent;
import model.items.Item;
import model.items.ItemRarity;
import model.items.ItemRarityComponent;
import model.items.ItemTile;
import model.items.ItemTileComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
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
					
					Room room = (Room)Engine.getInstance().getEntityByComponent(this);
					Cell cell = room.getCell(new Pair<Integer, Integer>(x, y));
					
					if(!cell.getAnimations().isEmpty()) {
						Collections.sort(cell.getAnimations());
						Animation animation = cell.getAnimations().get(0);
						AnimationTile animationTile = animation.getComponentByClass(AnimationTileComponent.class).getAnimationTile();
						asciiPanel.write(x, y, animationTile.tile, animationTile.color);
					}
					else if(cell.getEntity() != null) {
						Entity entity = cell.getEntity();
						EntityTile entityTile = entity.getComponentByClass(EntityTileComponent.class).getEntityTile();
						asciiPanel.write(x, y, entityTile.tile, entityTile.color);
					}
					else if(!cell.getItems().isEmpty()) {
						Collections.sort(cell.getItems());
						Item item = cell.getItems().get(0);
						ItemTileComponent itemTileComponent = item.getComponentByClass(ItemTileComponent.class);
						ItemRarityComponent itemRarityComponent = item.getComponentByClass(ItemRarityComponent.class);
						
						asciiPanel.write(x, y, itemTileComponent.getItemTile().tile, itemRarityComponent.getItemRarity().color);
					}
					else {
						asciiPanel.write(x, y, cell.getGround().tile, cell.getGround().color);
					}
				}
			}
		}
	}
}
