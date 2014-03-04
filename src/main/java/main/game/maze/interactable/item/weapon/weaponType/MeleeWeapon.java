package main.game.maze.interactable.item.weapon.weaponType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.mechanics.damageCalculation.CombatType;

public class MeleeWeapon implements WeaponType {

	@Override
	public CombatType getCombatType() {
		return CombatType.MELEE;
	}

	@Override
	public double getAttackPoints(Creature attacker) {
		double points = 0;
		points += attacker.getStats().getStrength();
		points += attacker.getWeapon().getStats().getStrength();
		points += attacker.getArmour().getStats().getStrength();
		return points;
	}
}
