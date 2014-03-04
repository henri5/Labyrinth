package main.game.maze.room;

import java.awt.Point;

import main.game.maze.Direction;
import main.game.maze.Maze;

public class SimpleRoom extends Room{

	public SimpleRoom(Maze maze, Point point){
		super(maze, point);
	}
	
	public SimpleRoom(Maze maze, Room previousRoom, Direction directionOfPreviousRoom) {
		super(maze, previousRoom, directionOfPreviousRoom);
	}
}
