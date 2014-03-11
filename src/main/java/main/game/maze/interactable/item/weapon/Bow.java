package main.game.maze.interactable.item.weapon;

import main.game.Config;
import main.game.maze.interactable.item.weapon.weaponType.RangeWeapon;
import main.game.maze.mechanics.stats.ItemStats;

public class Bow extends Weapon{
	private static final int DELAY = 600;
	private static final int DAMAGE = 10;
	private static final int RANGE = 70;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "weapon_bow.png";
	private static final String DESCRIPTION = "Basic bow. You can shoot enemy from a distance with this.";
	private static final ItemStats STATS = new ItemStats(0,3,0,0);
	public static final String NAME = "bow";
	
	public Bow(){
		super(NAME, IMAGE_SRC, DESCRIPTION, STATS);
		weaponRange = RANGE;
		weaponDamage = DAMAGE;
		weaponDelay = DELAY;
		weaponType = new RangeWeapon();
	}
}
