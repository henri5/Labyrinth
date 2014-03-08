package main.game.ui.gameinterface;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.game.util.Size;

public interface GameInterface {
	void drawInterface(Graphics g, Point cornerOfScreen, Size screenSize);
	void mousePressed(MouseEvent me);
	void keyPressed(KeyEvent e);
}
