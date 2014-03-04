package main.game.maze.interactable.creature.monster;

import java.awt.Dimension;

import main.game.Config;
import main.game.maze.interactable.item.Coins;
import main.game.maze.interactable.item.armour.LeatherArmour;
import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;
import main.game.maze.interactable.item.weapon.Bow;
import main.game.maze.mechanics.lootTable.LootEntry;
import main.game.maze.mechanics.lootTable.LootTable;
import main.game.maze.mechanics.stats.Stats;
import main.game.util.Util;

public class Ranger extends Monster{
	private static final int HEALTH = 50;
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_CREATURES + "ranger.png";
	private static final LootTable LOOT_TABLE;
	static {
		LOOT_TABLE = new LootTable.Builder()
			.addLootEntry(new LootEntry(100,300,new Coins(),2))
			.addLootEntry(new LootEntry(2,4,new Fish(),3))
			.addLootEntry(new LootEntry(1,2,new Cake(),1))
			.build();
	}

	public Ranger() {
		super("ranger", new Dimension(SIZE_WIDTH,SIZE_HEIGHT));
		weapon = new Bow();
		armour = new LeatherArmour();
		image = Util.readImage(IMAGE_SRC);
		stats = new Stats(1,2,1,1,HEALTH,Monster.MOVEMENT_SPEED);
		lootTable = LOOT_TABLE;
	}

}
