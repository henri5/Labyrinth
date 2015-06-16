package main.game.maze.interactable.item.behaviours.rightclickbehaviour;

import java.util.ArrayList;
import java.util.List;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;

public class EquippableItem implements RightClickBehaviour{
	private static final EquippableItem instance = new EquippableItem();
	
	private EquippableItem(){}
	
	@Override
	public Option[] getOptions(Item item, Player player) {
		List<Option> options = new ArrayList<Option>();
		if (player.ownsItem(item)){
			options.add(Option.DROP);
		} else {
			options.add(Option.PICKUP);
		}
		return options.toArray(new Option[0]);
	}

	public static RightClickBehaviour getInstance() {
		return instance;
	}

}
