package model.entities;

import model.Energy;
import model.Tile;
import model.turns.PlayerTurnHandler;

public class Player extends Creature {
	public Player() {
		super(Tile.PLAYER, new PlayerTurnHandler(), new Energy(60));
		
		this.hp = 100f;
		this.hpBasic = 50f;
		this.hpBasicRegenRate = 1.0f;
		
		this.manaBasicRegenRate = 1.0f;
		
		this.basicArmor = 5;
		
		this.strength = 5;
		this.agility = 5;
		this.intelligence = 5;
	}
}
