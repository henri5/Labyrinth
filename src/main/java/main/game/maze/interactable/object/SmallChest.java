package main.game.maze.interactable.object;

import java.awt.Dimension;

import main.game.Config;
import main.game.maze.interactable.item.Coins;
import main.game.maze.mechanics.lootTable.LootEntry;
import main.game.maze.mechanics.lootTable.LootTable;

public class SmallChest extends Chest {
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;
	private static final String NAME = "small chest";
	private static final Dimension imageSize = new Dimension(WIDTH, HEIGHT);
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_OBJECTS + "chest_normal.png";
	private static final LootTable LOOT_TABLE;
	static {
		LOOT_TABLE = new LootTable.Builder()
			.addLootEntry(new LootEntry(1000,10000,new Coins(),1))
			.build();
	}
	
	public SmallChest(){
		super(NAME, IMAGE_SRC, imageSize, LOOT_TABLE);
	}
}
