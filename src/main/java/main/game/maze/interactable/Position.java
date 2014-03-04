package main.game.maze.interactable;

import java.awt.Point;

import main.game.maze.room.Room;

public class Position {
	private Point point;
	private Room room;
	
	public Position(Point point, Room room){
		this.point = new Point(point);
		this.room = room;
	}
	
	public Position(Position position){
		this.point = position.getPoint();
		this.room = position.getRoom();
	}
	
	/*public Position(Point point){
		this.point = new Point(point);
	}*/

	public Position() {}
	
	public Point getPoint() {
		return new Point(point);
	}

	public void setPoint(Point point) {
		this.point = new Point(point);
	}
	
	public void setPoint(int x, int y) {
		this.point = new Point(x, y);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (!(o instanceof Position)){
			return false;
		}
		Position p = (Position) o;
		if (point == null) {
			if (p.point != null)
				return false;
		} else if (!point.equals(p.point))
			return false;
		if (room == null) {
			if (p.room != null)
				return false;
		} else if (!room.equals(p.room))
			return false;
		return true;
	}
	
	
}
