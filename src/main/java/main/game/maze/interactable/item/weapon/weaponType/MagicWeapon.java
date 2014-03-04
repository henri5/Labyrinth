package main.game.maze.interactable.item.weapon.weaponType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.mechanics.damageCalculation.CombatType;

public class MagicWeapon implements WeaponType {

	@Override
	public CombatType getCombatType() {
		return CombatType.MAGIC;
	}

	@Override
	public double getAttackPoints(Creature attacker) {
		double points = 0;
		points += attacker.getStats().getIntelligence();
		points += attacker.getWeapon().getStats().getIntelligence();
		points += attacker.getArmour().getStats().getIntelligence();
		return points;
	}
}
