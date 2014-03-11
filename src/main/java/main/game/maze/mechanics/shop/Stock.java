package main.game.maze.mechanics.shop;

import java.util.ArrayList;
import java.util.List;

import main.game.maze.interactable.creature.player.Player;
import main.game.maze.interactable.item.Coins;
import main.game.maze.interactable.item.Item;
import main.game.maze.interactable.item.ItemFactory;

import com.google.common.collect.ImmutableList;

public class Stock {
	private ImmutableList<StockItem> items;
	
	private Stock(){}
	
	public List<StockItem> getStockItems(){
		return items;
	}
	
	public static class Builder{
		private List<StockItem> items = new ArrayList<StockItem>();
		
		public Builder(){}
		
		public Builder addItem(StockItem item){
			items.add(item);
			return this;
		}
		
		public Builder addItems(StockItem... items){
			for (StockItem item: items){
				addItem(item);
			}
			return this;
		}
		
		public Stock build(){
			Stock stock = new Stock();
			stock.items = ImmutableList.copyOf(items);
			return stock;
		}	
	}

	public void sellItem(int selectedSlot, Player player) {
		if (selectedSlot < 0 || selectedSlot >= items.size()){
			return;
		}
		StockItem stockItem = items.get(selectedSlot);
		Item item = player.getItemForClass(Coins.class);
		if (item instanceof Coins){
			Coins coins = (Coins) item;
			synchronized (coins) {
				if (coins.getQuantity() > stockItem.getPrice()){
					Item newItem = ItemFactory.getItemForName(stockItem.getItem().getName());
					if (player.addItem(newItem)){
						coins.removeQuantity(stockItem.getPrice());
					}
				} else if (coins.getQuantity() == stockItem.getPrice()) {
					Item newItem = ItemFactory.getItemForName(stockItem.getItem().getName());
					if (player.addItem(newItem)){
						player.removeItem(item);
					}
				}
				
			}
				
		}
	}
}
