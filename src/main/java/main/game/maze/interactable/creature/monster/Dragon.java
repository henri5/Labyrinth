package main.game.maze.interactable.creature.monster;

import java.awt.Dimension;

import main.game.Config;
import main.game.maze.interactable.item.Coins;
import main.game.maze.interactable.item.armour.NoArmour;
import main.game.maze.interactable.item.weapon.Sword;
import main.game.maze.mechanics.lootTable.LootEntry;
import main.game.maze.mechanics.lootTable.LootTable;
import main.game.maze.mechanics.stats.Stats;
import main.game.util.Util;

public class Dragon extends Monster {
	private static final int HEALTH = 100;
	private static final int SIZE_WIDTH = 32;
	private static final int SIZE_HEIGHT = 32;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_CREATURES + "dragon.png";
	private static final LootTable LOOT_TABLE;
	static {
		LOOT_TABLE = new LootTable.Builder()
			.addLootEntry(new LootEntry(1000,10000,new Coins(),1))
			.build();
	}
	
	public Dragon() {
		super("dragon", new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
		weapon = new Sword();
		armour = new NoArmour();
		image = Util.readImage(IMAGE_SRC);
		stats = new Stats(4,4,4,4,HEALTH,Monster.MOVEMENT_SPEED);
		lootTable = LOOT_TABLE;
	}

}
