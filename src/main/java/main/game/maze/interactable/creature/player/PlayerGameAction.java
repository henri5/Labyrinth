package main.game.maze.interactable.creature.player;

import main.game.GameAction;
import main.game.MainController;
import main.game.system.Session;

public class PlayerGameAction implements GameAction {
	private Session session;
	
	public PlayerGameAction(Session session){
		this.session = session;
		MainController.addGameAction(this);
	}

	@Override
	public void doAction() {

	}

}
