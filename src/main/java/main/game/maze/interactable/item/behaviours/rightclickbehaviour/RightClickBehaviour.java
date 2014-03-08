package main.game.maze.interactable.item.behaviours.rightclickbehaviour;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;

public interface RightClickBehaviour {
	Option[] getOptions(Item item, Player player);

}
