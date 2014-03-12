package main.game;

import main.game.maze.Maze;
import main.game.maze.interactable.creature.monster.MonsterGameAction;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.creature.player.PlayerGameAction;
import main.game.ui.GameWindow;

public class MainController {
	private static GameClock gameClock;
	private static GameWindow gameWindow; //TODO: replace passing around gamewindow to get current maze with some new session instance, which holds recent data
	
	public static void main(String[] args) {
		gameClock = new GameClock();
		Maze maze = new Maze();
		gameWindow = new GameWindow(maze);
		Player player = new Player("bob");
		maze.setPlayer(player);
		new PlayerGameAction(gameWindow);
		new MonsterGameAction(gameWindow);
		gameWindow.setVisible(true);	
		gameClock.run();
	}
	
	public static void addGameAction(GameAction gameAction){
		gameClock.addGameAction(gameAction);
	}

	public static void disposeAction(GameAction gameAction) {
		gameClock.removeGameAction(gameAction);
	}

	public static void reset() {
		Maze maze = new Maze();
		Player player = new Player("bob");
		maze.setPlayer(player);
		gameWindow.setMaze(maze);
		gameWindow.closeMenuInterface();
	}
}
