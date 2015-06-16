package main.game.maze.builder;

import java.util.List;

import main.game.maze.Maze;
import main.game.maze.interactable.item.Key;
import main.game.maze.room.Room;

public interface Builder {

	boolean makeKeyDoor(int currentRoomCount, int maxRoomCount);
	Key placeKeyInMaze(List<Room> currentRooms);
	void createCriticalPath();
	void createStartingRoom();
	void buildLabyrinth();
}
