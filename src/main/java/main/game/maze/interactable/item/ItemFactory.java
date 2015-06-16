package main.game.maze.interactable.item;

import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;

public class ItemFactory {
	private ItemFactory(){}
	
	public static Item getItemForName(String name){
		switch (name){
			case Cake.NAME: return new Cake();
			case Fish.NAME: return new Fish();
			case Coins.NAME: return new Coins();
			default:
				throw new IllegalArgumentException("undefined item name: " + name);
		}
	}
}
