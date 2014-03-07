package main.game.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import main.game.Config;
import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Maze;

public class GameWindow extends JFrame implements GameAction {
	private static final long serialVersionUID = 2068148394786292635L;

	public GameWindow(Maze maze){
		setTitle("Labyrinth");
		getContentPane().setPreferredSize(new Dimension(Config.SIZE_WINDOW_WIDTH, Config.SIZE_WINDOW_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board board = new Board(maze);
		MiniMap miniMap = new MiniMap(maze);
		KeyBag keyBag = new KeyBag(maze);
		PlayerPanel playerPanel = new PlayerPanel(maze);
		setLayout(null);
		add(board);
		add(miniMap);
		add(keyBag);
		add(playerPanel);
		board.setBounds(0, 0, Config.SIZE_WINDOW_BOARD_WIDTH, Config.SIZE_WINDOW_BOARD_HEIGHT);
		miniMap.setBounds(Config.SIZE_WINDOW_BOARD_WIDTH, 0, Config.SIZE_WINDOW_MINIMAP_WIDTH, Config.SIZE_WINDOW_MINIMAP_HEIGHT);
		keyBag.setBounds(0, Config.SIZE_WINDOW_BOARD_HEIGHT, Config.SIZE_WINDOW_KEYBAG_WIDTH, Config.SIZE_WINDOW_KEYBAG_HEIGHT);
		playerPanel.setBounds(Config.SIZE_WINDOW_BOARD_WIDTH, Config.SIZE_WINDOW_MINIMAP_HEIGHT, 
				Config.SIZE_WINDOW_PLAYERPANEL_WIDTH, Config.SIZE_WINDOW_PLAYERPANEL_HEIGHT);
		pack();
		setLocationRelativeTo(null);
		
		MainController.addGameAction(this);
	}
	
	@Override
	public void doAction() {
		repaint();		
	}
}
