package main.game.maze.interactable.item.behaviours.rightclickbehaviour;

import java.util.ArrayList;
import java.util.List;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.food.Food;

public class ConsumableFood implements RightClickBehaviour{
	private static final ConsumableFood instance = new ConsumableFood();
	
	private ConsumableFood(){}
	
	@Override
	public Option[] getOptions(Item item, Player player) {
		if (!(item instanceof Food)){
			throw new IllegalArgumentException("item is not food");
		}
		List<Option> options = new ArrayList<Option>();
		if (player.ownsItem(item)){
			options.add(Option.EAT);
			options.add(Option.DROP);
		} else {
			options.add(Option.PICKUP);
		}
		return options.toArray(new Option[0]);
	}
	
	public static final ConsumableFood getInstance(){
		return instance;
	}

}
