package main.game.maze.interactable.item;

import java.awt.Dimension;
import main.game.Config;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.behaviours.ItemBehaviourFactory;
import main.game.maze.interactable.item.behaviours.ItemType;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;

public class Coins extends Stackable {
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "coins.png";
	public static final String NAME = "coins";
	private static final Dimension imageSize = new Dimension(SIZE_WIDTH, SIZE_HEIGHT);
	private static final ItemType itemType = ItemType.COINS;
	
	private final RightClickBehaviour rightClickBehaviour = ItemBehaviourFactory.getRightClickBehaviour(itemType);

	public Coins(){
		this(1);
	}
	
	public Coins(int quantity){
		super(NAME, IMAGE_SRC, imageSize, quantity);
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
		case PICKUP: pickUp(player); break;
		case DROP: player.drop(this); break;
		default: return;
		}
	}

}
