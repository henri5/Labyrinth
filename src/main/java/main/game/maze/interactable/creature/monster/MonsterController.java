package main.game.maze.interactable.creature.monster;

import java.util.Iterator;

import main.game.GameAction;
import main.game.MainController;
import main.game.maze.Maze;
import main.game.maze.room.Room;

public class MonsterController implements GameAction{
	private Maze maze;
	public MonsterController(Maze maze){
		this.maze = maze;
		MainController.addGameAction(this);
	}
	@Override
	public void doAction() {
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
