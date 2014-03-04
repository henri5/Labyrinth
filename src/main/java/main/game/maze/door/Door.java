package main.game.maze.door;

import main.game.maze.interactable.creature.player.Player;
import main.game.maze.room.Room;

public interface Door {
	public Room getRoom();
	public boolean unlock(Player player);
	public boolean isLocked();
}
