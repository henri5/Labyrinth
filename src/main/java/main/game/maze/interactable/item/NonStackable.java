package main.game.maze.interactable.item;

import java.awt.Dimension;

import main.game.maze.interactable.creature.player.Player;

public abstract class NonStackable extends Item{

	public NonStackable(String name, String imageSrc, Dimension imagesize) {
		super(name, imageSrc, imagesize);
	}
	
	public void pickUp(Player player) {
		if (player.isCloseToInteractable(this)){
			if (player.hasItemSpace()){
				if (!player.ownsItem(this)){
					getPosition().getRoom().removeDroppedItem(this);
					resetPosition();
					player.addItem(this);
				}
			}
		}
	}

}
