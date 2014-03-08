package main.game.maze.mechanics.damageCalculation;

import main.game.maze.interactable.creature.Creature;

public interface DamageCalculation {
	int getDamage(Creature attacker, Creature defender);
}
