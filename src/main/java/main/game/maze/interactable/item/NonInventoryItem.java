package main.game.maze.interactable.item;

import main.game.util.Size;

public abstract class NonInventoryItem extends Item {

	public NonInventoryItem(String name, String imageSrc, Size imagesize,
			String description) {
		super(name, imageSrc, imagesize, description);
	}
}
