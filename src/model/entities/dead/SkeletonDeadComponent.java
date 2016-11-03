package model.entities.dead;

import generator.EntityGenerator;
import model.Room;
import model.items.Item;
import model.items.ItemTile;
import pattern.Engine;
import util.Pair;

public class SkeletonDeadComponent extends EnemyDeadComponent {

	@Override
	protected void dropCorpse(Room room, Pair<Integer, Integer> positionEntity) {
		Item corpse = EntityGenerator.newGrayItem("Pile of bones", ItemTile.PILE_OF_BONES);
		room.getCell(positionEntity).getItems().add(corpse);
		Engine.getInstance().addEntity(corpse);
	}
}
