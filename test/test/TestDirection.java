package test;

import static org.junit.Assert.*;

import java.awt.Point;

import main.game.maze.Direction;

import org.junit.Test;

public class TestDirection {
	
	@Test
	public void testGetCoordinates(){
		Point north = new Point(0,-1);
		Point east = new Point(1,0);
		Point south = new Point(0,1);
		Point west = new Point(-1,0);
		assertEquals(north, Direction.NORTH.getCoordinates());
		assertEquals(east, Direction.EAST.getCoordinates());
		assertEquals(south, Direction.SOUTH.getCoordinates());
		assertEquals(west, Direction.WEST.getCoordinates());
	}
}
