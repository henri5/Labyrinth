package main.game.maze.interactable.item.weapon.weaponType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.mechanics.damageCalculation.CombatType;

public interface WeaponType {
	public CombatType getCombatType();

	public double getAttackPoints(Creature attacker);
}
