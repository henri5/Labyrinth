package main.game.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

import main.game.Config;
import main.game.maze.Maze;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Key;
import main.game.util.Util;

public class KeyBag extends JPanel {
	private static final long serialVersionUID = 6640663680636318930L;

	private Maze maze;
	public KeyBag(Maze maze){
		this.maze = maze;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		setBackground(Config.COLOR_BACKGROUND);
		drawPlayerKeys(g);
	}

	private void drawPlayerKeys(Graphics g) {
		Player player = maze.getPlayer();
		for (int i = 0; i < player.getKeys().size(); i++){
			Image image = player.getKeys().get(i).getImage();
			Point point = new Point(Config.PADDING_KEY_IMAGE + i*(Config.PADDING_KEY_IMAGE + Key.SIZE_IMAGE_KEYBAG.width),
					Config.PADDING_KEY_IMAGE);
			Util.drawImage(g, image, point, Key.SIZE_IMAGE_KEYBAG);
		}
	}
}
