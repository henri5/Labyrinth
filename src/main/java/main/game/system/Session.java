package main.game.system;

import main.game.maze.Maze;
import main.game.maze.interactable.creature.player.Player;

public class Session {
	private Maze maze;
	
	public Session(){
		maze = new Maze();
		Player player = new Player("bob");
		maze.setPlayer(player);
	}
	
	public void reset(){
		maze = new Maze();
		Player player = new Player("bob");
		maze.setPlayer(player);
	}
	
	public Maze getMaze(){
		return maze;
	}
}
