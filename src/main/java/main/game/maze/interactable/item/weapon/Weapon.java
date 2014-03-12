package main.game.maze.interactable.item.weapon;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.NonStackable;
import main.game.maze.interactable.item.behaviours.ItemBehaviourFactory;
import main.game.maze.interactable.item.behaviours.ItemType;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;
import main.game.maze.interactable.item.weapon.weaponType.WeaponType;
import main.game.maze.mechanics.stats.ItemStats;
import main.game.util.Size;

public abstract class Weapon extends NonStackable{
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final Size imageSize = new Size(SIZE_WIDTH, SIZE_HEIGHT);
	private static final ItemType itemType = ItemType.WEAPON;
	private static final RightClickBehaviour rightClickBehaviour = ItemBehaviourFactory.getRightClickBehaviour(itemType);
	private final String shortDescription;
	int weaponRange;
	int weaponDamage;
	int weaponDelay;
	private final ItemStats itemStats;
	WeaponType weaponType;

	public Weapon(String name, String imageSrc, String description, ItemStats stats) {
		super(name, imageSrc, imageSize, description);
		itemStats = stats;
		shortDescription = String.format("str: %d dex: %d int: %d sta: %d",
				itemStats.getStrength(), itemStats.getDexterity(),
				itemStats.getIntelligence(), itemStats.getStamina());
		
	}

	public int getWeaponRange() {
		return weaponRange;
	}

	public int getWeaponDelay() {
		return weaponDelay;
	}

	@Override
	public Option[] getOptions(Player player) {
		return rightClickBehaviour.getOptions(this, player);
	}
	
	@Override
	public void resetPosition(){
		getPosition().getRoom().removeDroppedItem(this);
		setPosition(null);
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public ItemStats getStats() {
		return itemStats;
	}

	public int getDamage() {
		return weaponDamage;
	}
	
	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case PICKUP: tryPickUp(player); break;
		case EQUIP: player.equip(this); break;
		case UNEQUIP: player.unequip(this); break;
		case DROP: player.drop(this); break;
		default: tryPickUp(player); break;
		}
	}

	@Override
	public String getShortDescription() {
		return shortDescription;
	} 
}
