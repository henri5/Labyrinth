package main.game.maze.interactable.object;

import java.awt.Dimension;
import java.awt.Image;

import main.game.maze.interactable.Interactable;
import main.game.maze.interactable.Position;
import main.game.util.Util;

public abstract class RoomObject implements Interactable {
	private final boolean isPassable;
	private final String name;
	private final Dimension imageSize;
	private final Image image;
	private Position position;
	
	public RoomObject(String name, String imageSrc, Dimension imageSize, boolean isPassable) {
		this.name = name;
		this.imageSize = imageSize;
		this.isPassable = isPassable;
		image = Util.readImage(imageSrc);
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

	public boolean isPassable() {
		return isPassable;
	}
}
