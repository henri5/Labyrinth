package main.game.maze.interactable.object;

import java.awt.Dimension;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.mechanics.lootTable.LootTable;

public abstract class Chest extends RoomObject {
	private static final boolean IS_PASSABLE = false;
	private final LootTable lootTable;
	
	public Chest(String name, String imageSrc, Dimension imageSize, LootTable lootTable) {
		super(name, imageSrc, imageSize, IS_PASSABLE);
		this.lootTable = lootTable;
	}

	@Override
	public Option[] getOptions(Player player) {
		return new Option[]{Option.OPEN};
	}

	@Override
	public void doInteract(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAction(Option optionFinal, Player player) {
		// TODO Auto-generated method stub
		
	}

}
