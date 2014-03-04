package main.game.maze.interactable.item.armour;

import main.game.maze.interactable.item.armour.armourType.MagicArmour;
import main.game.maze.mechanics.stats.ItemStats;


public class RobeArmour extends Armour{
	public RobeArmour(){
		itemStats = new ItemStats(0,0,2,4);
		armourType = new MagicArmour();
	}
}
