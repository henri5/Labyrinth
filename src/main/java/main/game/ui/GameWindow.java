package main.game.ui;

import java.awt.Dimension;
import javax.swing.JFrame;

import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Maze;
import main.game.ui.menu.MenuInterface;
import main.game.ui.misc.KeyPressListener;
import main.game.ui.playerpanel.PlayerPanel;
import main.game.util.Size;

public class GameWindow extends JFrame implements GameAction {
	private static final long serialVersionUID = 2068148394786292635L;
	public static final int HEIGHT = Board.HEIGHT + KeyBag.HEIGHT;
	public static final int WIDTH = Board.WIDTH + MiniMap.WIDTH;
	public static final Size SIZE = new Size(WIDTH, HEIGHT);
	private Maze maze;
	
	public GameWindow(Maze maze){
		this.maze = maze;
		setTitle("Labyrinth");
		getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board board = new Board(this);
		MiniMap miniMap = new MiniMap(this);
		KeyBag keyBag = new KeyBag(this);
		PlayerPanel playerPanel = new PlayerPanel(this);
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
		board.addKeyListener(new KeyPressListener(this));
		MenuInterface menu = new MenuInterface(this);
		menu.setOpaque(false);
		setGlassPane(menu);
		board.setFocusable(true);
		pack();
		setLocationRelativeTo(null);
		
		MainController.addGameAction(this);
	}
	
	@Override
	public void doAction() {
		repaint();		
	}

	public void closeMenuInterface() {
		getGlassPane().setVisible(false);
	}

	public void openMenuInterface() {
		getGlassPane().setVisible(true);
	}
	
	public boolean isMenuInterfaceOpen(){
		return getGlassPane().isVisible();
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public Maze getMaze(){
		return maze;
	}
}
