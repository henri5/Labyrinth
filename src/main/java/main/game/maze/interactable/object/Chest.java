package main.game.maze.interactable.object;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.mechanics.lootTable.LootTable;
import main.game.util.Size;

public abstract class Chest extends RoomObject {
	private static final boolean IS_PASSABLE = false;
	private final LootTable lootTable;
	private boolean opened = false;
	
	public Chest(String name, String imageSrc, Size imageSize, LootTable lootTable) {
		super(name, imageSrc, imageSize, IS_PASSABLE);
		this.lootTable = lootTable;
	}

	@Override
	public Option[] getOptions(Player player) {
		return new Option[]{Option.OPEN};
	}

	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case OPEN: open(player); break;
		default: open(player); break;
		}
		
	}

	private void open(Player player) {
		if (player.isCloseToInteractable(this)){
			if (!opened){
				opened = true;
				for (Item item: lootTable.getRandomDrops()){
					item.dropAt(player.getPosition());
					item.tryPickUp(player);
				}
			}			
		}
	}
	
	public String getName(){
		if (opened){
			return super.getName() + " (empty)";
		}
		return super.getName();
		
	}

}
