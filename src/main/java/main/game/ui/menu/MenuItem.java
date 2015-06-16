package main.game.ui.menu;

import main.game.ui.GameWindow;

public interface MenuItem {

	String getText();
	void click(GameWindow gameWindow);

}
