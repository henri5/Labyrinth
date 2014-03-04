package main.game.maze;

import java.awt.Point;

/**
 * Changing Direction coordinate values will *fuck* things up. Don't change.
 * E.g. door location drawing
 * @author Henri
 *
 */
public enum Direction {
	NORTH(0,-1), EAST(1,0), SOUTH(0,1), WEST(-1,0);
	
	private final Point coordinates;
	
	private Direction(int x, int y){
		coordinates = new Point(x,y);
	}
	
	public Point getCoordinates(){
		return new Point(coordinates);
	}
	
	public Direction getOpposite(){
		switch(this){
		case NORTH: return SOUTH;
		case EAST: return WEST;
		case SOUTH: return NORTH;
		case WEST: return EAST;
		default: throw new IllegalArgumentException("no opposite direction exists for " + toString());
		}
	}

	public Point add(Point p) {
		return new Point(coordinates.x + p.x, coordinates.y + p.y);
	}
}
