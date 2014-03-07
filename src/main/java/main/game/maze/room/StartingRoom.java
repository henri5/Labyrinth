package main.game.maze.room;

import java.awt.Point;

import main.game.maze.Maze;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.object.Merchant;
import main.game.maze.interactable.object.RoomObject;
import main.game.util.Util;

public class StartingRoom extends Room {

	public StartingRoom(Maze maze, Point point) {
		super(maze, point);
		RoomObject roomObject = new Merchant();
		roomObject.setPosition(new Position(Util.placeInMiddleOf(getRoomSize(), roomObject.getImageSize()), this));
		addRoomObject(roomObject);
	}
	
}
