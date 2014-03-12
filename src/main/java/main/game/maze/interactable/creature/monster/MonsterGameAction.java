package main.game.maze.interactable.creature.monster;

import java.util.Iterator;

import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Maze;
import main.game.maze.room.Room;
import main.game.ui.GameWindow;

public class MonsterGameAction implements GameAction{
	private GameWindow gameWindow;
	public MonsterGameAction(GameWindow gameWindow){
		this.gameWindow = gameWindow;
		MainController.addGameAction(this);
	}
	
	@Override
	public void doAction() {
		Maze maze = gameWindow.getMaze();
		for (int i = 0; i < maze.getWidth(); i++){
			for (int j = 0; j < maze.getHeight(); j++){
				Room room = maze.getRooms().get(i).get(j);
				if (room != null){
					if (!room.isLocked()){
						if (maze.getPlayer().getPosition().getRoom() != room){
							for (Monster monster: room.getMonsters()){
								monster.actIdle();
							}
						} else {
							for (Monster monster: room.getMonsters()){
								monster.target(maze.getPlayer());
							}
						}
						for (Iterator<Monster> it = room.getMonsters().iterator(); it.hasNext();){
							Monster monster = it.next();
							if (monster.isDead()){
								monster.die();
								it.remove();
							}
						}					
					}
				}
			}
		}
	}

}
