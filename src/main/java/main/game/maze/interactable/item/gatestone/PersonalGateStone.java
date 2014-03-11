package main.game.maze.interactable.item.gatestone;

import main.game.Config;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.player.Player;

public class PersonalGateStone extends GateStone{
	private static final String IMAGE_SRC = Config.IMAGES_FOLDER_ITEMS + "gatestone.png";
	private static final String NAME = "PersonalGateStone";
	private static final String DESCRIPTION = "You can teleport to this magical stone only one.";
	
	public PersonalGateStone(Player player){
		super(IMAGE_SRC, player, NAME, DESCRIPTION);
	}
	
	@Override
	public void dropGateStone(){
		setPosition(new Position(getPlayer().getPosition()));
	}
	
	@Override
	public void teleportTo() {
		if (exists()){
			getPlayer().setPosition(getPosition());
			resetPosition();
		}
	}
}
