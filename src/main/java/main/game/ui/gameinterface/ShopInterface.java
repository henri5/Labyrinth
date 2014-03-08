package main.game.ui.gameinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.game.maze.interactable.creature.player.Player;
import main.game.maze.mechanics.shop.Stock;
import main.game.ui.Board;
import main.game.util.Size;
import main.game.util.Util;

public class ShopInterface implements GameInterface {
	private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 100);
	private static final Color COLOR_BACKGROUND = new Color(50, 50, 50, 250);
	private static final Size SIZE_INTERFACE = new Size(400,400);
	private final Stock stock;
	private final Player player;
	
	public ShopInterface(Stock stock, Player player){
		this.stock = stock;
		this.player = player;
	}
	
	public void drawInterface(Graphics g, Point cornerOfScreen, Size screenSize){
		screenSize = new Size(Board.WIDTH, Board.HEIGHT); //TODO: immutable dimension class, only then i can throw it around
		g.setColor(COLOR_TRANSPARENT);
		g.fillRect(cornerOfScreen.x, cornerOfScreen.y, screenSize.width, screenSize.height);
		g.setColor(COLOR_BACKGROUND);
		Point interfaceCorner = Util.placeInMiddleOf(screenSize, SIZE_INTERFACE);
		g.fillRect(interfaceCorner.x+cornerOfScreen.x, interfaceCorner.y+cornerOfScreen.y, SIZE_INTERFACE.width, SIZE_INTERFACE.height);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		//player.setInterface(null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_ESCAPE: player.setInterface(null); break;
		default: break;
		}
	}
	
	
}
