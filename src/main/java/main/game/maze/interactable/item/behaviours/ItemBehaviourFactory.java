package main.game.maze.interactable.item.behaviours;

import main.game.maze.interactable.item.behaviours.rightclickbehaviour.ConsumableFood;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.EquippableItem;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.UsableItem;

public final class ItemBehaviourFactory {

	public static RightClickBehaviour getRightClickBehaviour(ItemType itemType){
		switch(itemType){
		case EDIBLE: return ConsumableFood.getInstance();
		case COINS: return UsableItem.getInstance();
		default:
			throw new IllegalArgumentException("undefined item type: " + itemType);
		}		
	}
}
