package main.game.maze.interactable.item;

import main.game.Config;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.behaviours.ItemBehaviourFactory;
import main.game.maze.interactable.item.behaviours.ItemType;
import main.game.maze.interactable.item.behaviours.rightclickbehaviour.RightClickBehaviour;
import main.game.util.Size;

public class Coins extends Stackable {
	private static final int SIZE_WIDTH = 20;
	private static final int SIZE_HEIGHT = 20;
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "coins.png";
	private static final Size imageSize = new Size(SIZE_WIDTH, SIZE_HEIGHT);
	private static final ItemType itemType = ItemType.COINS;
	private static final String DESCRIPTION = "You can spend those at merchant to buy goods.";
	private static final String DESCRIPTION_SHORT = "";
	public static final String NAME = "coins";
	
	private final RightClickBehaviour rightClickBehaviour = ItemBehaviourFactory.getRightClickBehaviour(itemType);

	public Coins(){
		this(1);
	}
	
	public Coins(int quantity){
		super(NAME, IMAGE_SRC, imageSize, quantity, DESCRIPTION);
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
		default: return;
		}
	}

	@Override
	public String getShortDescription() {
		return DESCRIPTION_SHORT;
	}

}
