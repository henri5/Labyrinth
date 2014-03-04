package main.game.maze.interactable.item.armour;

import main.game.maze.interactable.item.armour.armourType.RangeArmour;
import main.game.maze.mechanics.stats.ItemStats;


public class LeatherArmour extends Armour{
	public LeatherArmour(){
		itemStats = new ItemStats(0,2,0,4);
		armourType = new RangeArmour();
	}
}
