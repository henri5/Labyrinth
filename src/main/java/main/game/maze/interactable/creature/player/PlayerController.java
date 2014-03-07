package main.game.maze.interactable.creature.player;

import main.game.Config;
import main.game.GameAction;
import main.game.MainController;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.Key;
import main.game.maze.interactable.item.food.Food;
import main.game.maze.interactable.item.weapon.NoWeapon;
import main.game.maze.interactable.item.weapon.Weapon;

public class PlayerController {
	private static final String TAG = "PlayerController";
	private Player player;
	public PlayerController(Player player){
		this.player = player;
	}
	
	public void pickUpItems(){
		pickUpKeys();
		pickUpGroupGateStone();
		pickUpDroppedItems();
	}

	private void pickUpDroppedItems() {
		if (player.getPosition().getRoom().hasDroppedItems()){
			for (Item item: player.getPosition().getRoom().getDroppedItems()){
				if (player.isCloseToInteractable(item)){
					item.pickUp(player);
					return;
				}
			}
		}
	}

	private void pickUpGroupGateStone() {
		if (player.getGroupGateStone().exists()){
			if (player.getPosition().getRoom() == player.getGroupGateStone().getPosition().getRoom()){
				if (player.isCloseToInteractable(player.getGroupGateStone())){
					player.getGroupGateStone().resetPosition();
				}
			}
		}
	}

	void pickUpKeys() {
		Key key = player.getPosition().getRoom().getKey();
		if (key != null){
			if (player.isCloseToInteractable(key)){
				key.pickUp(player);
			}
		}
	}
	
	void respawn() {
		if (!player.getGroupGateStone().exists()){
			player.getGroupGateStone().dropGateStone();
		}
		player.getStats().resetHealth();
		player.setStartingPosition();
	}
	
	void teleportToBase() {
		MainController.addGameAction(new GameAction() {
			final long startTeleportTime = System.currentTimeMillis();
			final Position startTeleportPosition= new Position(player.getPosition());
			final long startLastBeingAttackedTime = player.getLastBeingAttackedTime();
			
			@Override
			public void doAction() {
				if (startTeleportTime < System.currentTimeMillis() - Config.DELAY_TELEPORT_TO_BASE){
					player.setStartingPosition();
					MainController.disposeAction(this);
					return;
				}
				if (!startTeleportPosition.equals(player.getPosition())){
					System.out.println(TAG + ".teleportToBase: teleport interrupted by moving");
					MainController.disposeAction(this);
					return;
				}
				if (startLastBeingAttackedTime != player.getLastBeingAttackedTime()){
					System.out.println(TAG + ".teleportToBase: teleport interrupted by being attacked");
					MainController.disposeAction(this);
					return;
				}
				
			}
		});
	}

	void addItem(Item item) {
		if (player.hasItemSpace()){
			player.getItems().add(item);
		} else {
			throw new IllegalStateException("player has no room for extra stuff");
		}
	}

	void drop(Item item) {
		if (!player.hasEquipped(item)){
			if (player.getItems().contains(item)){
				player.getItems().remove(item);
				item.setPosition(player.getPosition());
				player.getPosition().getRoom().addDroppedItem(item);
			}
		} else {
			System.out.println(TAG + ".drop: cant drop, equipped item");
		}
	}

	void equip(Item item) {
		if (item instanceof Weapon){
			System.out.println(TAG + ".equip: equipping " + item.getName());
			player.setWeapon((Weapon) item);
		} else {
			throw new IllegalArgumentException(TAG + ".equip: cant equip it");
		}
	}

	void unequip(Item item) {
		if (item instanceof Weapon){
			if (player.getWeapon() == item){
				System.out.println(TAG + ".unequip: unequipped " + item.getName());
				player.setWeapon(new NoWeapon());
			}
		}
	}

	void eat(Food food) {
		if (player.ownsItem(food)){
			player.getStats().heal(food.getHealAmount());
			player.getItems().remove(food);
		}
	}
	
	

}
