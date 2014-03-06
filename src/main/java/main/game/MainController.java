package main.game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.game.maze.Maze;
import main.game.maze.interactable.creature.player.Player;
import main.game.ui.Board;
import main.game.ui.KeyBag;
import main.game.ui.MiniMap;
import main.game.ui.PlayerPanel;

public class MainController {
	private static GameClock gameClock;
	
	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		gameClock = new GameClock(jframe);
		jframe.setTitle("Labyrinth");
		jframe.getContentPane().setPreferredSize(new Dimension(Config.SIZE_WINDOW_WIDTH, Config.SIZE_WINDOW_HEIGHT));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Maze maze = new Maze();
		maze.setPlayer(new Player("bob"));
		jframe.setLayout(null);
		Board board = new Board(maze);
		MiniMap miniMap = new MiniMap(maze);
		KeyBag keyBag = new KeyBag(maze);
		PlayerPanel playerPanel = new PlayerPanel(maze);
		jframe.add(board);
		jframe.add(miniMap);
		jframe.add(keyBag);
		jframe.add(playerPanel);
		board.setBounds(0, 0, Config.SIZE_WINDOW_BOARD_WIDTH, Config.SIZE_WINDOW_BOARD_HEIGHT);
		miniMap.setBounds(Config.SIZE_WINDOW_BOARD_WIDTH, 0, Config.SIZE_WINDOW_MINIMAP_WIDTH, Config.SIZE_WINDOW_MINIMAP_HEIGHT);
		keyBag.setBounds(0, Config.SIZE_WINDOW_BOARD_HEIGHT, Config.SIZE_WINDOW_KEYBAG_WIDTH, Config.SIZE_WINDOW_KEYBAG_HEIGHT);
		playerPanel.setBounds(Config.SIZE_WINDOW_BOARD_WIDTH, Config.SIZE_WINDOW_MINIMAP_HEIGHT, 
				Config.SIZE_WINDOW_PLAYERPANEL_WIDTH, Config.SIZE_WINDOW_PLAYERPANEL_HEIGHT);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);	
		gameClock.run();
	}
	
	public static void addGameAction(GameAction gameAction){
		gameClock.addGameAction(gameAction);
	}

	public static void disposeAction(GameAction gameAction) {
		gameClock.removeGameAction(gameAction);
	}

}
