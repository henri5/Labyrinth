package main.game.maze.interactable.object;

import java.awt.Dimension;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.mechanics.lootTable.LootTable;

public abstract class Chest extends RoomObject {
	private static final boolean IS_PASSABLE = false;
	private final LootTable lootTable;
	private boolean opened = false;
	
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
		doAction(Option.OPEN, player);
	}

	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case OPEN: open(player); break;
		default: throw new IllegalArgumentException("Illegal option: " + option);
		}
		
	}

	private void open(Player player) {
		if (player.isCloseToInteractable(this)){
			if (!opened){
				opened = true;
				for (Item item: lootTable.getRandomDrops()){
					item.dropAt(player.getPosition());
					if (player.hasItemSpace()){
						item.pickUp(player);;
					}
				}
			}			
		}
	}

}
