package main.game.maze.interactable.item.weapon.weaponType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.mechanics.damageCalculation.CombatType;
import main.game.maze.mechanics.stats.ItemStats;
import main.game.maze.mechanics.stats.Stats;

public class TypelessWeapon implements WeaponType {

	@Override
	public CombatType getCombatType() {
		return CombatType.TYPELESS;
	}

	@Override
	public double getAttackPoints(Creature attacker) {
		double points = 0;
		Stats stats = attacker.getStats();
		points += stats.getStrength();
		points += stats.getDexterity();
		points += stats.getIntelligence();
		ItemStats armourStats = attacker.getArmour().getStats();
		points += armourStats.getStrength();
		points += armourStats.getDexterity();
		points += armourStats.getIntelligence();
		ItemStats weaponStats = attacker.getWeapon().getStats();
		points += weaponStats.getStrength();
		points += weaponStats.getDexterity();
		points += weaponStats.getIntelligence();
		return points/3;
	}
}
