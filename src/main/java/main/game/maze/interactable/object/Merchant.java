package main.game.maze.interactable.object;

import main.game.Config;
import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.food.Cake;
import main.game.maze.interactable.item.food.Fish;
import main.game.maze.mechanics.shop.Stock;
import main.game.maze.mechanics.shop.StockItem;
import main.game.ui.gameinterface.ShopInterface;
import main.game.util.Size;

public class Merchant extends RoomObject {
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;
	private static final String NAME = "merchant";
	private static final boolean IS_PASSABLE = true;
	private static final Size imageSize = new Size(WIDTH, HEIGHT);
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_OBJECTS + "merchant.png";
	private static final Stock STOCK;
	static {
		STOCK = new Stock.Builder().addItems(new StockItem(new Cake(), 1000), 
					new StockItem(new Fish(), 500)).build();
	}
	
	public Merchant() {
		super(NAME, IMAGE_SRC, imageSize, IS_PASSABLE);
	}

	@Override
	public Option[] getOptions(Player player) {
		return new Option[]{Option.TRADE};
	}

	@Override
	public void doAction(Option option, Player player) {
		switch (option){
		case TRADE: player.setInterface(new ShopInterface(STOCK, player)); break;
		default: player.setInterface(new ShopInterface(STOCK, player)); break;
		}
	}

}
