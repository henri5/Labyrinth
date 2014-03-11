package main.game.maze.interactable.item.food;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.NonStackable;
import main.game.maze.interactable.item.behaviours.ItemBehaviourFactory;
import main.game.maze.interactable.item.behaviours.ItemType;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;
import main.game.util.Size;

public abstract class Food extends NonStackable {
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final Size imageSize = new Size(SIZE_WIDTH, SIZE_HEIGHT);
	private static final ItemType itemType = ItemType.EDIBLE;
	private static final RightClickBehaviour rightClickBehaviour = ItemBehaviourFactory.getRightClickBehaviour(itemType);
	private final int healAmount;
	private final String shortDescription;
	
	public Food(int healAmount, String name, String imageSrc, String description) {
		super(name, imageSrc, imageSize, description);
		if (healAmount <= 0){
			throw new IllegalArgumentException("heal amount must be greater than zero");
		}
		this.healAmount = healAmount;
		shortDescription = String.format("heals %d", healAmount);
	}

	@Override
	public Option[] getOptions(Player player) {
		return rightClickBehaviour.getOptions(this, player);
	}

	@Override
	public void doInteract(Player player) {
		doAction(Option.PICKUP, player);
	}

	@Override
	public void resetPosition() {
		getPosition().getRoom().removeDroppedItem(this);
		setPosition(null);
	}

	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case PICKUP: tryPickUp(player); break;
		case DROP: player.drop(this); break;
		case EAT: player.eat(this); break;
		default: return;
		}
	}

	public int getHealAmount() {
		return healAmount;
	}
	
	@Override
	public String getShortDescription(){
		return shortDescription;
	}
	
}
