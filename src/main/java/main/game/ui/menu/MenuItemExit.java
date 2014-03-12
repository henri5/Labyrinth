package main.game.ui.menu;

import main.game.ui.GameWindow;

public class MenuItemExit implements MenuItem {
	private static final String TEXT = "Exit";

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void click(GameWindow gameWindow) {
		System.out.println("Bye");
		System.exit(0);
	}
}
