package main.game.ui.gameinterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface GameInterface {
	void drawInterface(Graphics g, Point cornerOfScreen, Dimension screenSize);
}
