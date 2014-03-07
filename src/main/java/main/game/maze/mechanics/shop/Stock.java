package main.game.maze.mechanics.shop;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private List<StockItem> items;
	private Stock(){
		
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
			stock.items = items;
			return stock;
		}	
	}
}
