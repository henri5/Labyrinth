package main.game.ui.playerpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import main.game.maze.interactable.Option;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.Stackable;
import main.game.ui.GameWindow;
import main.game.util.Size;
import main.game.util.Util;
import static main.game.ui.playerpanel.PlayerPanel.*;

public class PlayerPanelInventory implements PlayerPanelInterface {
	private static final Color COLOR_INVENTORY_SLOT_STROKE = Color.GRAY;
	private static final Color COLOR_INVENTORY_SLOT_CONTENT_EQUIPPED = Color.LIGHT_GRAY;
	private static final int PADDING_INVENTORY_INTERNAL = 5;
	private static final int PADDING_INVENTORY_EXTERNAL = 20;

	private int imageSize;
	private GameWindow gameWindow;
	
	public PlayerPanelInventory(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		calculateInventorySlotSize();
	}
	
	@Override
	public void draw(Graphics g) {
		drawInventorySlots(g);
		drawItems(g);
	}

	private void calculateInventorySlotSize() {
		imageSize = (WIDTH - 2 * PADDING_INVENTORY_EXTERNAL - 
				(INVENTORY_SLOTS_HORIZONTAL - 1) * PADDING_INVENTORY_INTERNAL) / INVENTORY_SLOTS_HORIZONTAL;
	}

	private void drawInventorySlots(Graphics g) {
		Player player = gameWindow.getMaze().getPlayer();
		for (int i = 0; i < INVENTORY_SLOTS_HORIZONTAL; i++){
			for (int j = 0; j < INVENTORY_SLOTS_VERTICAL; j++){
				Point imageCorner = getItemCorner(i,j);
				g.setColor(COLOR_INVENTORY_SLOT_STROKE);
				int inventorySlot = i+j*INVENTORY_SLOTS_HORIZONTAL;
				if (player.getItems().size() > inventorySlot){
					if (player.hasEquipped(player.getItems().get(inventorySlot))){
						g.setColor(COLOR_INVENTORY_SLOT_CONTENT_EQUIPPED);
					}
				}
				g.fillOval(imageCorner.x, imageCorner.y, imageSize, imageSize);
				g.setColor(COLOR_BACKGROUND);
				g.fillOval(imageCorner.x+4, imageCorner.y+4, imageSize-8, imageSize-8);
			}
		}		
	}
	
	private void drawItems(Graphics g) {
		List<Item> items = gameWindow.getMaze().getPlayer().getItems();
		Iterator<Item> it = items.iterator();
		for (int i = 0; i < INVENTORY_SLOTS_VERTICAL; i++){
			for (int j = 0; j < INVENTORY_SLOTS_HORIZONTAL; j++){
				if (it.hasNext()){
					Item item = it.next();
					Point imageCorner = getItemCorner(j,i);
					Image image = item.getImage();
					g.drawImage(image, imageCorner.x, imageCorner.y, imageSize, imageSize, null);
					if (item instanceof Stackable){
						Stackable sItem = (Stackable) item;
						String text = Integer.toString(sItem.getQuantity());
					    Util.drawTextSmall(g, imageCorner, text);						
					}
				} else {
					return;
				}
			}
		}		
	}
	
	private Point getItemCorner(int i, int j) {
		return new Point(PADDING_INVENTORY_EXTERNAL + i*(imageSize + PADDING_INVENTORY_INTERNAL), 
				PADDING_INVENTORY_EXTERNAL + SWITCHTAB_HEIGHT + j*(imageSize + PADDING_INVENTORY_INTERNAL));
	}
	
	
	
	
	private class RightClickMenu extends JPopupMenu {
		private static final long serialVersionUID = 6176663489076570027L;
		private Item item;
		private RightClickMenu(int inventorySlot){
			item = gameWindow.getMaze().getPlayer().getItems().get(inventorySlot);
			Option[] options = item.getOptions(gameWindow.getMaze().getPlayer());
			setFocusable(false);
			for (Option option: options){
				final Option optionFinal = option;
				JMenuItem menuItem = new JMenuItem(optionFinal.toString() + ": " + item.getName());
				menuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						item.doAction(optionFinal, gameWindow.getMaze().getPlayer());
					}
				});
		        add(menuItem);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		for (int i = 0; i < INVENTORY_SLOTS_HORIZONTAL; i++){
			for (int j = 0; j < INVENTORY_SLOTS_VERTICAL; j++){
				Point corner = getItemCorner(i,j);
				if (me.getPoint().x >= corner.x && me.getPoint().x < corner.x + imageSize){
					if (me.getPoint().y >= corner.y && me.getPoint().y < corner.y + imageSize){
						int inventorySlot = i+j*INVENTORY_SLOTS_HORIZONTAL;
						if (me.getButton() == MouseEvent.BUTTON1){
							doLeftClick(inventorySlot);
						} else if (me.getButton() == MouseEvent.BUTTON3){
							doRightClick(me, inventorySlot);
						}
					}
				}
			}
		}	
	}
	
	private void doRightClick(MouseEvent me, int inventorySlot) {
		if (gameWindow.getMaze().getPlayer().getItems().size() > inventorySlot){
			final RightClickMenu menu = new RightClickMenu(inventorySlot);
			
			menu.setVisible(true);	//to initalize menu and generate size
			menu.setVisible(false);
			Point point = me.getPoint();
			if (point.x > WIDTH - menu.getWidth()){
				point.setLocation(WIDTH - menu.getWidth(), point.y);
			}
			if (point.y > HEIGHT - menu.getHeight()){
				point.setLocation(point.x, HEIGHT - menu.getHeight());
			}
			menu.addMouseListener(new MouseAdapter() {
				Size dim = new Size(menu.getSize());
				@Override
				public void mouseExited(MouseEvent e) {
					if (!Util.clickedOn( e.getPoint(), new Point(0,0), dim, 0)){
						menu.setVisible(false);
					}
				}
			});
		    menu.show(me.getComponent(), point.x, point.y);			 
		}
	}

	private void doLeftClick(int inventorySlot) {
		Player player = gameWindow.getMaze().getPlayer();
		if (player.getItems().size() > inventorySlot){
			Item item = player.getItems().get(inventorySlot);
			Option[] options = item.getOptions(player);
			if (options.length > 0){
				item.doAction(options[0], player);
			} else {
				throw new IllegalStateException("every item should return atleast 1 option!: " + item.getName());
			}			 
		}
	}
}
