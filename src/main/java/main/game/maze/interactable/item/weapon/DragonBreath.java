package main.game.maze.interactable.item.weapon;

import main.game.Config;
import main.game.maze.interactable.item.weapon.weaponType.TypelessWeapon;
import main.game.maze.mechanics.stats.ItemStats;

public class DragonBreath extends Weapon {
	private static final int DELAY = 1200;
	private static final int DAMAGE = 40;
	private static final int RANGE = 60;
	private static final String IMAGE_SRC = Config.IMAGES_NULL;
	private static final String DESCRIPTION = "If you see this text, something probably went wrong.";
	private static final ItemStats STATS = new ItemStats(5,5,5,0);
	public static final String NAME = "dragonbreath";

	public DragonBreath() {
		super(NAME, IMAGE_SRC, DESCRIPTION, STATS);
		weaponRange = RANGE;
		weaponDamage = DAMAGE;
		weaponDelay = DELAY;
		weaponType = new TypelessWeapon();
	}

}
