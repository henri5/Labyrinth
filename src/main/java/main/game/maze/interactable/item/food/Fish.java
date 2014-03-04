package main.game.maze.interactable.item.food;

import main.game.Config;

public class Fish extends Food{
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "food_fish.png";
	private static final int HEAL_AMOUNT = 200;
	public static final String NAME = "fish";
	
	public Fish(){
		super(HEAL_AMOUNT, NAME, IMAGE_SRC);
	}
}
