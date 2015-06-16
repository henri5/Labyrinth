package main.game.maze.door;

import main.game.maze.interactable.creature.player.Player;
import main.game.maze.room.Room;

public interface Door {
	Room getRoom();
	boolean unlock(Player player);
	boolean isLocked();
}
