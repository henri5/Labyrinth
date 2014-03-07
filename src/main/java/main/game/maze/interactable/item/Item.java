package main.game.maze.interactable.item;

import java.awt.Dimension;
import java.awt.Image;
import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Position;
import main.game.maze.interactable.creature.player.Player;
import main.game.util.Util;

public abstract class Item implements Interactable{
	private Image image;
	private String name;
	private Position position;
	private Dimension imageSize;
	
	public Item(String name, String imageSrc, Dimension imagesize) {
		this.name = name;
		this.imageSize = imagesize;
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
	public Dimension getImageSize() {
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
