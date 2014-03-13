package main.game.ui.playerpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.game.system.Session;
import main.game.ui.Board;
import main.game.ui.KeyBag;
import main.game.ui.MiniMap;

public class PlayerPanel extends JPanel {
	private static final long serialVersionUID = 8598148008009578889L;
	static final Color COLOR_BACKGROUND = Color.BLACK;
	static final int SWITCHTAB_HEIGHT = 30;
	static final int INVENTORY_SLOTS_HORIZONTAL = 5;
	static final int INVENTORY_SLOTS_VERTICAL = 6;
	public static final int WIDTH = MiniMap.WIDTH;
	public static final int HEIGHT = Board.HEIGHT + KeyBag.HEIGHT - MiniMap.HEIGHT;
	public static final int INVENTORY_SIZE = INVENTORY_SLOTS_HORIZONTAL*INVENTORY_SLOTS_VERTICAL;

	private Session session;
	private PlayerPanelInterface openInterface;
	
	public PlayerPanel(Session session) {
		this.session = session;
		openInterface = new PlayerPanelInventory(session);
		addMouseListener(new MouceClickListener());
	}
	

	@Override
	public void paint(Graphics g){
		super.paint(g);
		setBackground(COLOR_BACKGROUND);
		openInterface.draw(g);
	}

	private class MouceClickListener extends MouseAdapter {
		public void mousePressed(MouseEvent me){
			if (me.getPoint().y < SWITCHTAB_HEIGHT){
				System.out
						.println("PlayerPanel.MouceClickListener.mousePressed()");
			}
			openInterface.mousePressed(me);
		}
	}
}
