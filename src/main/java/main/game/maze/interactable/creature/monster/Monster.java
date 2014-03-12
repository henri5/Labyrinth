package main.game.maze.interactable.creature.monster;

import main.game.Config;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.mechanics.lootTable.LootTable;
import main.game.util.Size;
import main.game.util.Util;

public abstract class Monster extends Creature implements Interactable{
	private MonsterController controller;
	static final int MOVEMENT_SPEED = 1;
	LootTable lootTable;
	
	public Monster(String name, Size imageSize) {
		super(name, imageSize);
		controller = new MonsterController(this);
		setStartingPosition();
	}

	public void actIdle() {
		controller.actIdle();
	}

	public void target(Creature creature) {
		controller.target(creature);
	}
	
	private void setStartingPosition() {
		getPosition().setPoint(Util.randomPointInArea(Config.SIZE_ROOM_WIDTH-imageSize.width, Config.SIZE_ROOM_HEIGHT-imageSize.height));
	}
	
	public void die(){
		controller.die();
	}
	
	@Override
	public Option[] getOptions(Player player){
		return new Option[]{Option.ATTACK};
	}
	
	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case ATTACK: player.attackCreature(this); break;
		default: player.attackCreature(this); break;
		}
	}
}
