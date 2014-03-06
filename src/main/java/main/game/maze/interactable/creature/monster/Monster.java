package main.game.maze.interactable.creature.monster;

import java.awt.Dimension;
import java.awt.Point;

import main.game.Config;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.mechanics.lootTable.LootTable;
import main.game.util.Util;

public abstract class Monster extends Creature implements Interactable{
	private static final int DELAY_MOVEMENT_IDLE = 1000;
	static final int MOVEMENT_SPEED = 1;

	private boolean destinationReached = true;
	private long destinationReachedTime;
	private Point destination;
	LootTable lootTable;
	
	public Monster(String name, Dimension imageSize) {
		super(name, imageSize);
		setStartingPosition();
	}

	public void actIdle() {
		if (destinationReached){
			destination = Util.randomPointInArea(Config.SIZE_ROOM_WIDTH-imageSize.width, Config.SIZE_ROOM_HEIGHT-imageSize.height);
			destinationReached = false;
		}
		if (Util.areasOverlap(getPosition().getPoint(), imageSize, destination, new Dimension(1,1))){
			destinationReached = true;
			destinationReachedTime = System.currentTimeMillis();
			return;
		}
		if (destinationReachedTime + DELAY_MOVEMENT_IDLE < System.currentTimeMillis()){
			moveAtDestination();
		}
		
	}

	private void moveAtDestination() {
		Point p = getPosition().getPoint();
		int dx = (int) (Math.signum(destination.x-p.x)*Math.min(Math.abs(destination.x-p.x), stats.getMovementSpeed()));
		int dy = (int) (Math.signum(destination.y-p.y)*Math.min(Math.abs(destination.y-p.y), stats.getMovementSpeed()));
		moveInRoom(dx, dy);
	}

	public void target(Creature creature) {
		if(isWithinRange(creature)){
			attackCreature(creature);
		} else {
			destination = creature.getPosition().getPoint();
			moveAtDestination();
		}
	}
	
	private void setStartingPosition() {
		getPosition().setPoint(Util.randomPointInArea(Config.SIZE_ROOM_WIDTH-imageSize.width, Config.SIZE_ROOM_HEIGHT-imageSize.height));
	}
	
	@Override
	public void doInteract(Player player) {
		doAction(Option.ATTACK, player);
	}
	
	public void die(){
		getPosition().getRoom().getMonsters().remove(this);
		Item[] items = lootTable.getRandomDrops();
		for (Item item: items){
			item.dropAt(getPosition());
		}
	}
	
	@Override
	public Option[] getOptions(Player player){
		return new Option[]{Option.ATTACK};
	}
	
	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case ATTACK: 
			player.attackCreature(this); 
			if (isDead()){
				die();
			}
			break;
		default: return;
		}
	}
}
