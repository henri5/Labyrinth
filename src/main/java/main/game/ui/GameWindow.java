package main.game.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Maze;

public class GameWindow extends JFrame implements GameAction {
	private static final long serialVersionUID = 2068148394786292635L;
	public static final int HEIGHT = Board.HEIGHT + KeyBag.HEIGHT;
	public static final int WIDTH = Board.WIDTH + MiniMap.WIDTH;

	public GameWindow(Maze maze){
		setTitle("Labyrinth");
		getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
		board.setBounds(0, 0, Board.WIDTH, Board.HEIGHT);
		miniMap.setBounds(Board.WIDTH, 0, MiniMap.WIDTH, MiniMap.HEIGHT);
		keyBag.setBounds(0, Board.HEIGHT, KeyBag.WIDTH, KeyBag.HEIGHT);
		playerPanel.setBounds(Board.WIDTH, MiniMap.HEIGHT, 
				PlayerPanel.WIDTH, PlayerPanel.HEIGHT);
		pack();
		setLocationRelativeTo(null);
		
		MainController.addGameAction(this);
	}
	
	@Override
	public void doAction() {
		repaint();		
	}
}
