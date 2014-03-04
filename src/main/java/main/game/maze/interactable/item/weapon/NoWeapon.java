package main.game.maze.interactable.item.weapon;

import main.game.maze.interactable.item.weapon.weaponType.TypelessWeapon;
import main.game.maze.mechanics.stats.ItemStats;

public class NoWeapon extends Weapon {
	private static final String NAME = "noWeapon";

	public NoWeapon(){
		super(NAME, null);
		weaponRange = 10;
		weaponDamage = 1;
		weaponDelay = 1000;
		itemStats = new ItemStats(0,0,0,0);
		weaponType = new TypelessWeapon();
	}
}
