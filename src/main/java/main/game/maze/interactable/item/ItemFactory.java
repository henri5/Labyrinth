package main.game.maze.interactable.item;

import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;
import main.game.maze.interactable.item.weapon.Bow;
import main.game.maze.interactable.item.weapon.Staff;
import main.game.maze.interactable.item.weapon.Sword;

public class ItemFactory {
	private ItemFactory(){
		throw new IllegalAccessError("must not be initiated");
	}
	
	public static Item getItemForName(String name){
		switch (name){
		case Cake.NAME: return new Cake();
		case Fish.NAME: return new Fish();
		case Bow.NAME: return new Bow();
		case Staff.NAME: return new Staff();
		case Sword.NAME: return new Sword();
		case Coins.NAME: return new Coins();
		default:
			throw new IllegalArgumentException("undefined item name: " + name);
		}
	}
}
