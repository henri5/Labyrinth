package main.game.maze.interactable.item.gatestone;

import main.game.Config;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.player.Player;

public class GroupGateStone extends GateStone{
	private static final String NAME = "GroupGateStone";
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "group_gatestone.png";
	private static final String DESCRIPTION = "You can teleport multiple times to this magical stone.";
	
	public GroupGateStone(Player player){
		super(IMAGE_SRC, player, NAME, DESCRIPTION);
	}
	
	@Override
	public void dropGateStone(){
		if (!exists()){
			setPosition(new Position(getPlayer().getPosition()));
		}
	}
	
	@Override
	public void teleportTo() {
		if (exists()){
			getPlayer().setPosition(getPosition());
		}
	}
}
