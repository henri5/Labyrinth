package main.game.maze.interactable;

import java.awt.Image;

import main.game.maze.interactable.creature.player.Player;
import main.game.util.Size;

public interface Interactable {
	Option[] getOptions(Player player);
	Position getPosition();
	String getName();
	Image getImage();
	Size getImageSize();
	void doAction(Option optionFinal, Player player);

}
