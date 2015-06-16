package main.game.maze.interactable.creature.player;

import main.game.Config;
import main.game.maze.Direction;
import main.game.maze.DummyObject;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.Creature;
import main.game.maze.interactable.item.Coins;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.Key;
import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;
import main.game.maze.interactable.item.food.Food;
import main.game.maze.interactable.item.gatestone.GateStone;
import main.game.maze.interactable.item.gatestone.GroupGateStone;
import main.game.maze.interactable.item.gatestone.PersonalGateStone;
import main.game.maze.room.Room;
import main.game.ui.gameinterface.GameInterface;
import main.game.ui.playerpanel.PlayerPanel;
import main.game.util.Size;
import main.game.util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Creature{
	private static final int HEALTH = 1000;
	private static final int MOVEMENT_SPEED = 5;
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final String IMAGE = Config.IMAGES_FOLDER_CREATURES + "player.png";
	
	private List<Key> keys = new ArrayList<Key>();
	private GateStone personalGateStone = new PersonalGateStone(this);
	private GateStone groupGateStone = new GroupGateStone(this);
	private Position startingPosition;
	private List<Item> items = new ArrayList<Item>();
	private PlayerController controller;
	private GameInterface gameInterface;
	
	public Player(String name){
		super(name, new Size(SIZE_WIDTH,SIZE_HEIGHT));
		controller = new PlayerController(this);
		image = Util.readImage(IMAGE);
		//increaseLevel(1000);
		addItem(new Coins(10000));
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
		controller.move(direction);
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
		controller.interactWithDoor();
	}

	public void moveTo(Point positionInRoom) {
		getPosition().setPoint(positionInRoom);
	}

	//TODO set movement speed
	public int getMovementSpeed() {
		return 5;
	}

	public void createStartingPosition(Room startingRoom) {
		startingPosition = new Position();
		startingPosition.setRoom(startingRoom);
		startingPosition.setPoint((Config.SIZE_ROOM_WIDTH-imageSize.width)/2,(Config.SIZE_ROOM_HEIGHT-imageSize.height)/2);
	}
	
	public void setStartingPosition() {
		setPosition(startingPosition);
	}

	public void interactWith(Option option, Interactable interactable) {
		if (interactable instanceof DummyObject){
			return;
		}
		if (getPosition().getRoom() == interactable.getPosition().getRoom()){
			interactable.doAction(option, this);
		}
	}

	public boolean hasItemSpace() {
		return items.size() < PlayerPanel.INVENTORY_SIZE;
	}

	public boolean addItem(Item item) {
		return controller.addItem(item);
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

	public void respawn() {
		controller.respawn();
	}

	public void setInterface(GameInterface gameInterface) {
		this.gameInterface = gameInterface;
	}
	
	public GameInterface getGameInterface(){
		return gameInterface;
	}

	public Item getItemForClass(Class<?> clazz) {
		for (Item item : items) {
			if (item.getClass().equals(clazz)){
				return item;
			}
		}
		return null;
	}

	public void removeItem(Item item) {
		items.remove(item);
	}
}
