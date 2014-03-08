package main.game.maze.interactable.creature.monster;

import java.awt.Point;

import main.game.Config;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.item.Item;
import main.game.util.Size;
import main.game.util.Util;

public class MonsterController {
	private final int DELAY_MOVEMENT_IDLE = 1000;
	private Monster monster;
	private boolean destinationReached = true;
	private long destinationReachedTime;
	private Point destination;
	
	public MonsterController(Monster monster) {
		this.monster = monster;
	}
	
	public void actIdle() {
		if (destinationReached){
			destination = Util.randomPointInArea(Config.SIZE_ROOM_WIDTH-monster.getImageSize().width, Config.SIZE_ROOM_HEIGHT-monster.getImageSize().height);
			destinationReached = false;
		}
		if (Util.areasOverlap(monster.getPosition().getPoint(), monster.getImageSize(), destination, new Size(1,1), 0)){
			destinationReached = true;
			destinationReachedTime = System.currentTimeMillis();
			return;
		}
		if (destinationReachedTime + DELAY_MOVEMENT_IDLE < System.currentTimeMillis()){
			moveAtDestination();
		}
		
	}

	public void target(Creature creature) {
		if(monster.isWithinRange(creature)){
			monster.attackCreature(creature);
		} else {
			destination = creature.getPosition().getPoint();
			moveAtDestination();
		}
	}

	public void moveAtDestination() {
		Point p = monster.getPosition().getPoint();
		int dx = (int) (Math.signum(destination.x-p.x)*Math.min(Math.abs(destination.x-p.x), monster.getStats().getMovementSpeed()));
		int dy = (int) (Math.signum(destination.y-p.y)*Math.min(Math.abs(destination.y-p.y), monster.getStats().getMovementSpeed()));
		monster.moveInRoom(dx, dy);
	}

	public void die() {
		monster.getPosition().getRoom().getMonsters().remove(this);
		Item[] items = monster.lootTable.getRandomDrops();
		for (Item item: items){
			item.dropAt(monster.getPosition());
		}
	}

}
