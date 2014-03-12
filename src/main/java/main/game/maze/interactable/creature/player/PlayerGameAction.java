package main.game.maze.interactable.creature.player;

import main.game.GameAction;
import main.game.MainController;
import main.game.ui.GameWindow;

public class PlayerGameAction implements GameAction {
	private GameWindow gameWindow;
	
	public PlayerGameAction(GameWindow gameWindow){
		this.gameWindow = gameWindow;
		MainController.addGameAction(this);
	}

	@Override
	public void doAction() {
		if (gameWindow.getMaze().getPlayer().isDead()){
			System.out.println("Am dead");
			gameWindow.getMaze().getPlayer().respawn();
		}
	}

}
