package main.game.maze.interactable.item.weapon;

import main.game.Config;
import main.game.maze.interactable.item.weapon.weaponType.RangeWeapon;
import main.game.maze.mechanics.stats.ItemStats;

public class Bow extends Weapon{
	private static final int DELAY = 600;
	private static final int DAMAGE = 10;
	private static final int RANGE = 70;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "weapon_bow.png";
	public static final String NAME = "bow";
	
	public Bow(){
		super(NAME, IMAGE_SRC);
		weaponRange = RANGE;
		weaponDamage = DAMAGE;
		weaponDelay = DELAY;
		itemStats = new ItemStats(0,3,0,0);
		weaponType = new RangeWeapon();
	}
}
