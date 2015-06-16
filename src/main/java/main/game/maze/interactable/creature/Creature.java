package main.game.maze.interactable.creature;

import main.game.Config;
import main.game.maze.interactable.Position;
import main.game.util.Size;

import java.awt.*;

public class Creature {
	private String name;
	protected Image image;
	protected Size imageSize;
	protected Position position = new Position();

	public Creature(String name, Size imageSize) {
		this.name = name;
		this.imageSize = imageSize;
	}
	
	public void setPosition(Position position){
		if (position == null){
			throw new IllegalArgumentException("cannot set player's position to null");
		}
		this.position = new Position(position);
	}

	public Position getPosition() {
		return position;
	}
	
	public Image getImage(){
		return image;
	}

	public Size getImageSize() {
		return imageSize;
	}
	
	public void moveInRoom(int dx, int dy){
		if (dx == 0 && dy == 0){
			return; //nothing to move
		}
		Point p = getPosition().getPoint();
		int newPositionX = p.x+dx;
		int newPositionY = p.y+dy;
		if (newPositionX < 0){
			newPositionX = 0;
		}
		if (newPositionY < 0){
			newPositionY = 0;
		}
		if (newPositionX > (Config.SIZE_ROOM_WIDTH-imageSize.width)){
			newPositionX = Config.SIZE_ROOM_WIDTH-imageSize.width;
		}
		if (newPositionY > (Config.SIZE_ROOM_HEIGHT-imageSize.width)){
			newPositionY = Config.SIZE_ROOM_HEIGHT-imageSize.width;
		}
		if (getPosition().getRoom().collidesWithRoomObject(new Point(newPositionX, newPositionY), getImageSize())){
			return;	//cant collide with an object
		}
		getPosition().setPoint(newPositionX, newPositionY);
	}
}
