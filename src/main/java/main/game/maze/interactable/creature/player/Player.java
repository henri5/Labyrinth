package main.game.maze.interactable.creature.player;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.game.Config;
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
	private PlayerController controller;
	
	public Player(String name){
		super(name, new Dimension(SIZE_WIDTH,SIZE_HEIGHT));
		new PlayerGameAction(this);
		controller = new PlayerController(this);
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
		controller.pickUpKeys();
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

	public int getMovementSpeed() {
		return stats.getMovementSpeed();
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
		controller.addItem(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void equip(Item item) {
		controller.equip(item);
	}

	public void teleportToBase(){
		controller.teleportToBase();
	}

	public void drop(Item item) {
		controller.drop(item);
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
		controller.unequip(item);
	}

	public boolean isCloseToInteractable(Interactable interactable) {
		if (getPosition().getRoom() != interactable.getPosition().getRoom()){
			return false;
		}
		Point p = getPosition().getPoint();
		return Util.areasOverlap(p, getImageSize(), interactable.getPosition().getPoint(), 
				interactable.getImageSize(), Config.MAX_INTERACTION_DISTANCE);
	}

	public boolean ownsItem(Item item) {
		return items.contains(item);
	}

	public void eat(Food food) {
		controller.eat(food);
	}	

	public void pickUpItems() {
		controller.pickUpItems();
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void respawn() {
		controller.respawn();
	}
}
