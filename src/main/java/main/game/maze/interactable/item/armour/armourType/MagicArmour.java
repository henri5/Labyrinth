package main.game.maze.interactable.item.armour.armourType;

import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.item.weapon.weaponType.WeaponType;
import main.game.maze.mechanics.damageCalculation.CombatType;

public class MagicArmour implements ArmourType {

	@Override
	public CombatType getCombatType() {
		return CombatType.MAGIC;
	}

	@Override
	public double getModifier(WeaponType weaponType) {
		switch (weaponType.getCombatType()) {
		case MAGIC: return 1.00;
		case MELEE: return 0.75;
		case RANGE: return 1.25;
		case TYPELESS: return 1.00;
		}
		throw new IllegalArgumentException("weapon has undefined combat type");
	}

	@Override
	public double getDefendpoints(Creature defender) {
		double points = 0;
		points += defender.getStats().getStamina();
		points += defender.getArmour().getStats().getStamina();
		return points;
	}

}
