package main.game.maze.interactable.item.weapon;

import main.game.Config;
import main.game.maze.interactable.item.weapon.weaponType.MagicWeapon;
import main.game.maze.mechanics.stats.ItemStats;

public class Staff extends Weapon{
	private static final int DELAY = 1200;
	private static final int DAMAGE = 22;
	private static final int RANGE = 50;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "weapon_staff.png";
	public static final String NAME = "staff";
	
	public Staff(){
		super(NAME, IMAGE_SRC);
		weaponRange = RANGE;
		weaponDamage = DAMAGE;
		weaponDelay = DELAY;
		itemStats = new ItemStats(0,0,3,0);
		weaponType = new MagicWeapon();
	}
}
