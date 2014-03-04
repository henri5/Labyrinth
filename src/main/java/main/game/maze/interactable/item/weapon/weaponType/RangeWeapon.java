package main.game.maze.interactable.item.weapon.weaponType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.mechanics.damageCalculation.CombatType;

public class RangeWeapon implements WeaponType {

	@Override
	public CombatType getCombatType() {
		return CombatType.RANGE;
	}

	@Override
	public double getAttackPoints(Creature attacker) {
		double points = 0;
		points += attacker.getStats().getDexterity();
		points += attacker.getWeapon().getStats().getDexterity();
		points += attacker.getArmour().getStats().getDexterity();
		return points;
	}
}
