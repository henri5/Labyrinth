package main.game.maze.room;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.game.maze.Direction;
import main.game.maze.Maze;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.object.RoomObject;
import main.game.maze.interactable.object.SmallChest;

public class ChestRoom extends Room{	
	public ChestRoom(Maze maze, Room previousRoom, Direction directionOfPreviousRoom) {
		super(maze, previousRoom, directionOfPreviousRoom);
		RoomObject roomObject = new SmallChest();
		roomObject.setPosition(new Position(new Point(0,0), this));
		addRoomObject(roomObject);
	}
}
