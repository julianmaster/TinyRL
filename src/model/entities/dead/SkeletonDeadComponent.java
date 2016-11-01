package model.entities.dead;

import generator.EntityGenerator;
import model.Room;
import model.RoomComponent;
import model.entities.KillEvent;
import model.items.ItemTile;
import pattern.Engine;
import pattern.Event;
import util.Pair;

public class SkeletonDeadComponent extends EnemyDeadComponent {

	@Override
	protected void dropCorpse(Room room, Pair<Integer, Integer> positionEntity) {
		room.getCell(positionEntity).getItems().add(EntityGenerator.newGrayItem("Pile of bones", ItemTile.PILE_OF_BONES));
	}
}
