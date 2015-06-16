package main.game;

import main.game.maze.interactable.creature.player.PlayerGameAction;
import main.game.system.GameClock;
import main.game.system.Session;
import main.game.ui.GameWindow;

public class MainController {
	private static GameClock gameClock;
	private static Session session;
	private static GameWindow gameWindow;
	
	public static void main(String[] args) {
		gameClock = new GameClock();
		session = new Session();
		gameWindow = new GameWindow(session);
		new PlayerGameAction(session);
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
