package main.game.maze.interactable.item.armour;

import main.game.maze.interactable.item.armour.armourType.ArmourType;
import main.game.maze.interactable.item.behaviours.ItemBehaviourFactory;
import main.game.maze.interactable.item.behaviours.ItemType;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;
import main.game.maze.mechanics.stats.ItemStats;

public abstract class Armour {
	private static final ItemType itemType = ItemType.ARMOUR;
	private static final RightClickBehaviour rightClickBehaviour = ItemBehaviourFactory.getRightClickBehaviour(itemType);
	ItemStats itemStats;
	ArmourType armourType;
	
	public ArmourType getArmourType() {
		return armourType;
	}

	public ItemStats getStats() {
		return itemStats;
	}
}
