package main.game.maze.interactable.item.armour;

import main.game.maze.interactable.item.armour.armourType.TypelessArmour;
import main.game.maze.mechanics.stats.ItemStats;

public class NoArmour extends Armour {
	public NoArmour(){
		itemStats = new ItemStats(0,0,0,0);
		armourType = new TypelessArmour();
	}
}
