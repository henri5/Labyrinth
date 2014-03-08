package main.game.maze.interactable.item;

import java.awt.Image;

import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.player.Player;
import main.game.util.Size;
import main.game.util.Util;

public abstract class Item implements Interactable{
	private Image image;
	private String name;
	private Position position;
	private Size imageSize;
	
	public Item(String name, String imageSrc, Size imagesize2) {
		this.name = name;
		this.imageSize = imagesize2;
		if (imageSrc != null){
			this.image = Util.readImage(imageSrc);
		}
	}

	public abstract void resetPosition();
	
	public abstract void pickUp(Player player);

	@Override
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position){
		if (position == null){
			this.position = null;
		} else {
			this.position = new Position(position);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Image getImage() {
		return image;
	}
	
	@Override
	public Size getImageSize() {
		return imageSize;
	}
	
	public void dropAt(Position position){
		setPosition(position);
		position.getRoom().addDroppedItem(this);
	}
	
	public void tryPickUp(Player player){
		if (player.isCloseToInteractable(this)){
			pickUp(player);
		}
	}
}
