package wpl.spring.service;

import java.util.List;

import wpl.spring.entity.Inventory;

public interface InventoryService {
	
public void addInventryItem(Inventory inventory);
	
	public Inventory getInventoryItem(int itemId);
	
	public List<Inventory> getInventory();
	
	public int updateInventoryItem(Inventory registry);
	
	public int deleteInventoryItem(int itemId);

}
