package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.Inventory;

public interface InventoryDao {
	
	public void addInventryItem(Inventory inventory);
	
	public Inventory getInventoryItem(int itemId);
	
	public List<Inventory> getInventory();
	
	public int updateInventoryItem(Inventory inventory);
	
	public int deleteInventoryItem(int itemId);

}
