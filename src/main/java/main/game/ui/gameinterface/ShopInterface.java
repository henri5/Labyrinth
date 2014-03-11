package main.game.ui.gameinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.game.Config;
import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Item;
import main.game.maze.mechanics.shop.Stock;
import main.game.maze.mechanics.shop.StockItem;
import main.game.util.Size;
import main.game.util.Util;

public class ShopInterface implements GameInterface {
	private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 100);
	private static final Color COLOR_BACKGROUND = new Color(50, 50, 50, 250);
	private static final Color COLOR_SLOT = new Color(60, 60, 60, 250);
	private static final Color COLOR_SLOT_SELECTED = new Color(70, 70, 70, 250);
	private static final int SLOTS_HORIZONTAL = 6;
	private static final int SLOTS_VERTICAL = 4;
	private static final int PADDING_SLOTS_EXTERNAL = 30;
	private static final int PADDING_SLOTS_INTERNAL = 10;
	private static final int PADDING_SLOTS_IMAGES = 5;
	private static final Size SIZE_STOCK_SLOT = new Size(60,60);
	private static final Size SIZE_DESCRIPTION;
	private static final Size SIZE_BUTTON_BUY;
	static {
		int height = 40;
		int spanSlotsWidth = 4;
		int widthDescription = spanSlotsWidth * (SIZE_STOCK_SLOT.width + PADDING_SLOTS_INTERNAL) - PADDING_SLOTS_INTERNAL;
		SIZE_DESCRIPTION = new Size(widthDescription, height);
		int widthButton = (SLOTS_HORIZONTAL - spanSlotsWidth) * (SIZE_STOCK_SLOT.width + PADDING_SLOTS_INTERNAL) - PADDING_SLOTS_INTERNAL;
		SIZE_BUTTON_BUY = new Size(widthButton, height);
	}
	private static final Size SIZE_INTERFACE;
	static {
		SIZE_INTERFACE = new Size(2 * PADDING_SLOTS_EXTERNAL + SLOTS_HORIZONTAL
				* (SIZE_STOCK_SLOT.width + PADDING_SLOTS_INTERNAL) - PADDING_SLOTS_INTERNAL,
				2 * PADDING_SLOTS_EXTERNAL + SLOTS_VERTICAL
				* (SIZE_STOCK_SLOT.width + PADDING_SLOTS_INTERNAL) + SIZE_DESCRIPTION.height);
				
	}
	private final Stock stock;
	private final Player player;
	private Point interfaceCorner;
	private int selectedSlot = -1;
	
	public ShopInterface(Stock stock, Player player){
		this.stock = stock;
		this.player = player;
	}
	
	public void drawInterface(Graphics g, Point cornerOfScreen, Size screenSize){
		drawBackgroundOverlay(g, cornerOfScreen, screenSize);
		Point interfaceCornerRelative = Util.placeInMiddleOf(screenSize, SIZE_INTERFACE);
		Point interfaceCornerAbsolute = interfaceCorner = new Point(interfaceCornerRelative.x + cornerOfScreen.x, interfaceCornerRelative.y + cornerOfScreen.y);
		drawInterfaceBackground(g, interfaceCornerAbsolute);
		drawInterfaceText(g, interfaceCornerAbsolute);
		drawStockItems(g, interfaceCornerAbsolute);
		drawDescription(g, interfaceCornerAbsolute);
		drawBuyButton(g, interfaceCornerAbsolute);
	}

	private void drawBuyButton(Graphics g, Point interfaceCornerAbsolute) {
		g.setColor(COLOR_SLOT);
		Point buttonCorner = new Point(interfaceCornerAbsolute.x + PADDING_SLOTS_EXTERNAL + PADDING_SLOTS_INTERNAL + SIZE_DESCRIPTION.width,
				interfaceCornerAbsolute.y+PADDING_SLOTS_EXTERNAL+(SLOTS_VERTICAL)*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.height));
		g.fillRect(buttonCorner.x, buttonCorner.y, SIZE_BUTTON_BUY.width, SIZE_BUTTON_BUY.height);
	}

	private void drawDescription(Graphics g, Point interfaceCornerAbsolute) {
		//g.setColor(COLOR_SLOT);
		Point descriptionCorner = new Point(interfaceCornerAbsolute.x + PADDING_SLOTS_EXTERNAL,
				interfaceCornerAbsolute.y+PADDING_SLOTS_EXTERNAL+(SLOTS_VERTICAL)*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.height));
		//g.fillRect(descriptionCorner.x, descriptionCorner.y, SIZE_DESCRIPTION.width, SIZE_DESCRIPTION.height);
		if (selectedSlot != -1){
			StockItem stockItem = stock.getStockItems().get(selectedSlot);
			Util.drawTextSmall(g, descriptionCorner, stockItem.getItem().getName(), stockItem.getItem().getShortDescription(), "Price: " + stockItem.getPrice());
		}
	}

	private void drawInterfaceText(Graphics g, Point interfaceCornerAbsolute) {
		Point textLocation = new Point(interfaceCornerAbsolute.x, interfaceCornerAbsolute.y + SIZE_INTERFACE.height);		
		Util.drawTextSmall(g, textLocation, String.format("Press %S to close the interface", KeyEvent.getKeyText(Config.KEYBIND_CLOSE_INTERFACE)));
	}

	private void drawInterfaceBackground(Graphics g,
			Point interfaceCornerAbsolute) {
		g.setColor(COLOR_BACKGROUND);
		g.fillRect(interfaceCornerAbsolute.x, interfaceCornerAbsolute.y, SIZE_INTERFACE.width, SIZE_INTERFACE.height);
	}

	private void drawBackgroundOverlay(Graphics g, Point cornerOfScreen,
			Size screenSize) {
		g.setColor(COLOR_TRANSPARENT);
		g.fillRect(cornerOfScreen.x, cornerOfScreen.y, screenSize.width, screenSize.height);
	}

	private void drawStockItems(Graphics g, Point interfaceCorner) {
		g.setColor(COLOR_SLOT);
		int stockSize = stock.getStockItems().size();
		Size imageSize = new Size(SIZE_STOCK_SLOT.width - 2*PADDING_SLOTS_IMAGES,SIZE_STOCK_SLOT.height - 2*PADDING_SLOTS_IMAGES);
		for (int i = 0; i < SLOTS_HORIZONTAL; i++) {
			for (int j = 0; j < SLOTS_VERTICAL; j++) {
				Point slotCorner = new Point(interfaceCorner.x+PADDING_SLOTS_EXTERNAL+i*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.width),
						interfaceCorner.y+PADDING_SLOTS_EXTERNAL+j*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.height));
				int slot = j * SLOTS_HORIZONTAL + i;
				if (slot == selectedSlot){
					g.setColor(COLOR_SLOT_SELECTED);
					g.fillRect(slotCorner.x, slotCorner.y, SIZE_STOCK_SLOT.width, SIZE_STOCK_SLOT.height);
					g.setColor(COLOR_SLOT);
				} else {
					g.fillRect(slotCorner.x, slotCorner.y, SIZE_STOCK_SLOT.width, SIZE_STOCK_SLOT.height);
				}
				if (slot < stockSize){
					StockItem stockItem = stock.getStockItems().get(slot);
					Util.drawImage(g, stockItem.getItem().getImage(), new Point(slotCorner.x+PADDING_SLOTS_IMAGES,slotCorner.y+PADDING_SLOTS_IMAGES), imageSize);
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		int stockSize = stock.getStockItems().size();
		Point clickPosition = me.getPoint();
		for (int i = 0; i < SLOTS_HORIZONTAL; i++) {
			for (int j = 0; j < SLOTS_VERTICAL; j++) {
				Point slotCorner = new Point(interfaceCorner.x+PADDING_SLOTS_EXTERNAL+i*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.width),
						interfaceCorner.y+PADDING_SLOTS_EXTERNAL+j*(PADDING_SLOTS_INTERNAL+SIZE_STOCK_SLOT.height));
				int slot = j * SLOTS_HORIZONTAL + i;
				if (slot < stockSize){
					if (Util.areasOverlap(clickPosition, new Size(1,1), slotCorner, SIZE_STOCK_SLOT, 0)){
						selectedSlot = slot;
						return;
					}
				}
			}
		}
		Point buyButtonCorner = new Point(interfaceCorner.x + PADDING_SLOTS_EXTERNAL + PADDING_SLOTS_INTERNAL + SIZE_DESCRIPTION.width,
				interfaceCorner.y + PADDING_SLOTS_EXTERNAL + SLOTS_VERTICAL * (PADDING_SLOTS_INTERNAL + SIZE_STOCK_SLOT.height));
		if (Util.areasOverlap(clickPosition, new Size(1,1), buyButtonCorner, SIZE_BUTTON_BUY, 0)){
			stock.sellItem(selectedSlot, player);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case Config.KEYBIND_CLOSE_INTERFACE: player.setInterface(null); break;
		default: break;
		}
	}
	
	
}
