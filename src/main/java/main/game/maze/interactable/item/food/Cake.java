package main.game.maze.interactable.item.food;

import main.game.Config;

public class Cake extends Food {
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "food_cake.png";
	private static final int HEAL_AMOUNT = 400;
	private static final String DESCRIPTION = "Seems odd to see a cake at such place, but monsters have birthdays too.";
	public static final String NAME = "cake";
	
	public Cake(){
		super(HEAL_AMOUNT, NAME, IMAGE_SRC, DESCRIPTION);
	}
}
