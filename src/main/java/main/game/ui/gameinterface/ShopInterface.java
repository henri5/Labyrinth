package main.game.ui.gameinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import main.game.Config;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.mechanics.shop.Stock;
import main.game.util.Util;

public class ShopInterface implements GameInterface {
	private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 100);
	private static final Color COLOR_BACKGROUND = new Color(50, 50, 50, 250);
	private static final Color COLOR_BUTTON_CLOSE = Color.CYAN;
	private static final Dimension SIZE_INTERFACE = new Dimension(400,400);
	private static final int PADDING_CLOSE_BUTTON = 10;
	private static final Dimension SIZE_BUTTON_CLOSE = new Dimension(20,20);
	private final Stock stock;
	private final Player player;
	
	public ShopInterface(Stock stock, Player player){
		this.stock = stock;
		this.player = player;
	}
	
	public void drawInterface(Graphics g, Point cornerOfScreen, Dimension screenSize){
		screenSize = new Dimension(Config.SIZE_WINDOW_BOARD_WIDTH, Config.SIZE_WINDOW_BOARD_HEIGHT); //TODO: immutable dimension class, only then i can throw it around
		g.setColor(COLOR_TRANSPARENT);
		g.fillRect(cornerOfScreen.x, cornerOfScreen.y, screenSize.width, screenSize.height);
		g.setColor(COLOR_BACKGROUND);
		Point interfaceCorner = Util.placeInMiddleOf(screenSize, SIZE_INTERFACE);
		g.fillRect(interfaceCorner.x+cornerOfScreen.x, interfaceCorner.y+cornerOfScreen.y, SIZE_INTERFACE.width, SIZE_INTERFACE.height);
		g.setColor(COLOR_BUTTON_CLOSE);
		g.fillRect(interfaceCorner.x+cornerOfScreen.x+SIZE_INTERFACE.width-PADDING_CLOSE_BUTTON-SIZE_BUTTON_CLOSE.width,
				interfaceCorner.y+cornerOfScreen.y+PADDING_CLOSE_BUTTON, SIZE_BUTTON_CLOSE.width, SIZE_BUTTON_CLOSE.height);
	}
}
