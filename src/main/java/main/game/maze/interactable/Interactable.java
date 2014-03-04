package main.game.maze.interactable;

import java.awt.Dimension;
import java.awt.Image;

import main.game.maze.interactable.creature.player.Player;

public interface Interactable {
	Option[] getOptions(Player player);
	void doInteract(Player player);
	Position getPosition();
	String getName();
	Image getImage();
	Dimension getImageSize();
	void doAction(Option optionFinal, Player player);

}
