package main.game.maze.interactable.creature.monster;

import java.util.Random;

public class MonsterFactory {
	private static final MonsterFactory instance = new MonsterFactory();
	
	private MonsterFactory(){}
	
	public static MonsterFactory getInstance(){
		return instance;
	}
	
	public Monster getNormalMonster(){
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(3);
		switch (randomNumber){
		case 0: return new Wizard();
		case 1: return new Knight();
		case 2: return new Ranger();
		default: throw new IllegalStateException("random number larger than monster count");
		}
	}
	
	public Monster getBossMonster(){
		return new Dragon();
	}
}
