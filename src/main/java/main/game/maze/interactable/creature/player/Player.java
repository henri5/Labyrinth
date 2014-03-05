package main.game.maze.interactable.creature.player;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.game.Config;
import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Direction;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.item.Coins;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.Key;
import main.game.maze.interactable.item.armour.NoArmour;
import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;
import main.game.maze.interactable.item.food.Food;
import main.game.maze.interactable.item.gatestone.GateStone;
import main.game.maze.interactable.item.gatestone.GroupGateStone;
import main.game.maze.interactable.item.gatestone.PersonalGateStone;
import main.game.maze.interactable.item.weapon.Bow;
import main.game.maze.interactable.item.weapon.NoWeapon;
import main.game.maze.interactable.item.weapon.Staff;
import main.game.maze.interactable.item.weapon.Sword;
import main.game.maze.interactable.item.weapon.Weapon;
import main.game.maze.mechanics.stats.Stats;
import main.game.maze.room.Room;
import main.game.util.Util;

public class Player extends Creature{
	private static final int HEALTH = 1000;
	private static final int MOVEMENT_SPEED = 5;
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final String IMAGE = Config.IMAGES_FOLDER_CREATURES + "player.png";
	
	private static final String TAG = "Player";
	private List<Key> keys = new ArrayList<Key>();
	private GateStone personalGateStone = new PersonalGateStone(this);
	private GateStone groupGateStone = new GroupGateStone(this);
	private Position startingPosition;
	private List<Item> items = new ArrayList<Item>();
	
	public Player(String name){
		super(name, new Dimension(SIZE_WIDTH,SIZE_HEIGHT));
		image = Util.readImage(IMAGE);
		stats = new Stats(5,5,5,10,HEALTH,MOVEMENT_SPEED);
		//increaseLevel(1000);
		weapon = new Sword();
		armour = new NoArmour();
		addItem(weapon);
		addItem(new Bow());
		addItem(new Staff());
		addItem(new Coins(1000));
		addItem(new Cake());
		addItem(new Fish());
	}

	
	public void addKey(Key key){
		keys.add(key);
	}
	public List<Key> getKeys(){
		return keys;
	}
	public void removeKey(Key key){
		keys.remove(key);
	}
		
	public void move(Direction direction){
		if (getPosition().getRoom().getDoorByDirection(direction) == null){
			return;
		} else if (getPosition().getRoom().getDoorByDirection(direction).isLocked()){
			getPosition().getRoom().getDoorByDirection(direction).unlock(this);
		} else {
			getPosition().setRoom(getPosition().getRoom().getDoorByDirection(direction).getRoom());
			Dimension area = new Dimension((Config.SIZE_ROOM_WIDTH-imageSize.width) * Math.abs(direction.getCoordinates().x),
					(Config.SIZE_ROOM_HEIGHT-imageSize.height) * Math.abs(direction.getCoordinates().y));
			Point p = getPosition().getPoint();
			getPosition().setPoint(Math.abs(area.width-p.x),Math.abs(area.height-p.y));
		}
	}

	public void pickUpKeys() {
		Key key = getPosition().getRoom().getKey();
		if (key != null){
			if (isCloseToItem(key)){
				key.pickUp(this);
			}
		}
	}

	public GateStone getGroupGateStone(){
		return groupGateStone;
	}
	
	public GateStone getPersonalGateStone(){
		return personalGateStone;
	}

	public void interactWithDoor() {
		Direction dir;
		if ((dir = getClosestDoorDirection()) != null){
			move(dir);
		}	
	}
	
	private Direction getClosestDoorDirection(){
		Point p = getPosition().getPoint();
		Point d = new Point((Config.SIZE_ROOM_WIDTH - Config.SIZE_DOOR_ROOM - SIZE_WIDTH)/2,
				(Config.SIZE_ROOM_HEIGHT - Config.SIZE_DOOR_ROOM - SIZE_HEIGHT)/2);
		Point k = new Point((Config.SIZE_ROOM_WIDTH - SIZE_WIDTH)/2, (Config.SIZE_ROOM_HEIGHT - SIZE_HEIGHT)/2);
		for (Direction dir: Direction.values()){
			if (!getPosition().getRoom().hasDoorAtDirection(dir)){
				continue;
			}
			Point l = dir.getCoordinates();
			Point s = new Point(d.x*Math.abs(l.y) + (l.x+Math.abs(l.x))*k.x,d.y*Math.abs(l.x) + (l.y+Math.abs(l.y))*k.y);
			Dimension f = new Dimension(Math.abs(l.y)*Config.SIZE_DOOR_ROOM, Math.abs(l.x)*Config.SIZE_DOOR_ROOM);
			//System.out.printf("am [%d,%d] trying [%d,%d] dimension %d,%d%n", p.x, p.y, s.x, s.y,f.width,f.height);
			if (p.x >= s.x && p.x <= s.x+f.width){
				if (p.y >= s.y && p.y <= s.y + f.height){
					return dir;
				}
			}			
		}	
		return null;
	}

	public void moveTo(Point positionInRoom) {
		getPosition().setPoint(positionInRoom);
	}

	public void pickUpItems() {
		pickUpKeys();
		pickUpGroupGateStone();
		pickUpDroppedItems();
	}

	private void pickUpDroppedItems() {
		if (getPosition().getRoom().hasDroppedItems()){
			for (Item item: getPosition().getRoom().getDroppedItems()){
				if (isCloseToItem(item)){
					item.pickUp(this);
					return;
				}
			}
		}
	}


	private void pickUpGroupGateStone() {
		if (groupGateStone.exists()){
			if (getPosition().getRoom() == groupGateStone.getPosition().getRoom()){
				if (isCloseToItem(groupGateStone)){
					groupGateStone.resetPosition();
				}
			}
		}
	}

	public int getMovementSpeed() {
		return stats.getMovementSpeed();
	}

	public void respawn() {
		if (!groupGateStone.exists()){
			groupGateStone.dropGateStone();
		}
		stats.resetHealth();
		setStartingPosition();
	}

	public void createStartingPosition(Room startingRoom) {
		startingPosition = new Position();
		startingPosition.setRoom(startingRoom);
		startingPosition.setPoint((Config.SIZE_ROOM_WIDTH-imageSize.width)/2,(Config.SIZE_ROOM_HEIGHT-imageSize.height)/2);
	}
	
	public void setStartingPosition() {
		setPosition(startingPosition);
	}

	public void interactWith(Interactable interactable) {
		interactable.doInteract(this);
	}
	
	@Override
	protected long getWeaponDelay() {
		return 0;
	}

	public boolean hasItemSpace() {
		return items.size() < Config.INVENTORY_COUNT_VERTICAL*Config.INVENTORY_COUNT_HORIZONTAL;
	}


	public void addItem(Item item) {
		if (hasItemSpace()){
			items.add(item);
		} else {
			throw new IllegalStateException("player has no room for extra stuff");
		}
	}


	public List<Item> getItems() {
		return items;
	}


	public void equip(Item item) {
		if (item instanceof Weapon){
			System.out.println(TAG + ".equip: equipping " + item.getName());
			weapon = (Weapon) item;
		} else {
			throw new IllegalArgumentException(TAG + ".equip: cant equip it");
		}
	}


	public void drop(Item item) {
		if (!hasEquipped(item)){
			if (items.contains(item)){
				items.remove(item);
				item.setPosition(position);
				getPosition().getRoom().addDroppedItem(item);
			}
		} else {
			System.out.println(TAG + ".drop: cant drop, equipped item");
		}
	}

	public boolean hasEquipped(Item item) {
		if (item instanceof Weapon){
			if (weapon == item){
				return true;
			}
		}
		return false;
	}

	public void unequip(Item item) {
		if (item instanceof Weapon){
			if (weapon == item){
				System.out.println(TAG + ".unequip: unequipped " + item.getName());
				weapon = new NoWeapon();
			}
		}
	}

	public void pickup(Item item) {
		if (item.getPosition() != null){
			if (item.getPosition().getRoom() == getPosition().getRoom()){
				if (isCloseToItem(item)){
					item.pickUp(this);		
				}
			}
		}
	}

	public boolean isCloseToItem(Item item) {
		if (getPosition().getRoom() != item.getPosition().getRoom()){
			return false;
		}
		Point p = getPosition().getPoint();
		return Util.areasOverlap(p, getImageSize(), item.getPosition().getPoint(), 
				item.getImageSize(), Config.PADDING_ITEM_PICKUP);
	}


	public void teleportToBase() {
		MainController.addGameAction(new GameAction() {
			final long startTeleportTime = System.currentTimeMillis();
			final Position startTeleportPosition= new Position(getPosition());
			final long startLastBeingAttackedTime = lastBeingAttackedTime;
			
			@Override
			public void doAction() {
				if (startTeleportTime < System.currentTimeMillis() - Config.DELAY_TELEPORT_TO_BASE){
					setStartingPosition();
					MainController.disposeAction(this);
					return;
				}
				if (!startTeleportPosition.equals(getPosition())){
					System.out.println(TAG + ".teleportToBase: teleport interrupted by moving");
					MainController.disposeAction(this);
					return;
				}
				if (startLastBeingAttackedTime != lastBeingAttackedTime){
					System.out.println(TAG + ".teleportToBase: teleport interrupted by being attacked");
					MainController.disposeAction(this);
					return;
				}
				
			}
		});
	}


	public boolean ownsItem(Item item) {
		return items.contains(item);
	}


	public void eat(Food food) {
		if (ownsItem(food)){
			stats.heal(food.getHealAmount());
			items.remove(food);
		}
	}	
}
