package main.game.ui.menu;

import main.game.MainController;
import main.game.ui.GameWindow;

public class MenuItemReset implements MenuItem {
	private static final String TEXT = "Reset";

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void click(GameWindow gameWindow) {
		MainController.reset();
	}
}
