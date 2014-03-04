package main.game.maze.interactable.item.armour;

import main.game.maze.interactable.item.armour.armourType.MeleeArmour;
import main.game.maze.mechanics.stats.ItemStats;


public class MetalArmour extends Armour{
	public MetalArmour(){
		itemStats = new ItemStats(2,0,0,4);
		armourType = new MeleeArmour();
	}
}
