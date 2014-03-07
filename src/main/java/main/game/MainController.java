package main.game;

import main.game.maze.Maze;
import main.game.maze.interactable.creature.player.Player;
import main.game.ui.GameWindow;

public class MainController {
	private static GameClock gameClock;
	
	public static void main(String[] args) {
		gameClock = new GameClock();
		Maze maze = new Maze();
		maze.setPlayer(new Player("bob"));
		GameWindow gameWindow = new GameWindow(maze);
		gameWindow.setVisible(true);	
		gameClock.run();
	}
	
	public static void addGameAction(GameAction gameAction){
		gameClock.addGameAction(gameAction);
	}

	public static void disposeAction(GameAction gameAction) {
		gameClock.removeGameAction(gameAction);
	}
}
