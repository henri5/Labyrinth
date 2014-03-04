package main.game.maze.interactable.item.armour.armourType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.item.weapon.weaponType.WeaponType;
import main.game.maze.mechanics.damageCalculation.CombatType;

public interface ArmourType {
	public CombatType getCombatType();

	public double getModifier(WeaponType weaponType);

	public double getDefendpoints(Creature defender);
}
