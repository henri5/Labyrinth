package main.game.maze.interactable.item;

import main.game.maze.interactable.creature.player.Player;
import main.game.util.Size;

public abstract class NonStackable extends Item{

	public NonStackable(String name, String imageSrc, Size imagesize) {
		super(name, imageSrc, imagesize);
	}
	
	public void pickUp(Player player) {
		if (player.hasItemSpace()){
			if (!player.ownsItem(this)){
				getPosition().getRoom().removeDroppedItem(this);
				resetPosition();
				player.addItem(this);
			}
		}
	}

}
