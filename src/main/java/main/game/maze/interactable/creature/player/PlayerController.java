package main.game.maze.interactable.creature.player;

import main.game.GameAction;
import main.game.MainController;

public class PlayerController implements GameAction {
	private Player player;
	public PlayerController(Player player){
		this.player = player;
		MainController.addGameAction(this);
	}
	
	@Override
	public void doAction() {
		if (player.isDead()){
			System.out.println("Am dead");
			player.respawn();
		}
	}

}
