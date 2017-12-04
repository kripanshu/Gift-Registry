package wpl.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wpl.spring.dao.InventoryDao;
import wpl.spring.entity.Inventory;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;

	@Override
	@Transactional
	public void addInventryItem(Inventory inventory) {
		inventoryDao.addInventryItem(inventory);

	}

	@Override
	@Transactional
	public Inventory getInventoryItem(int itemId) {
		return inventoryDao.getInventoryItem(itemId);
	}

	@Override
	@Transactional
	public List<Inventory> getInventory() {
		return inventoryDao.getInventory();
	}

	@Override
	@Transactional
	public int updateInventoryItem(Inventory inventory) {
		return inventoryDao.updateInventoryItem(inventory);
	}

	@Override
	@Transactional
	public int deleteInventoryItem(int itemId) {
		return inventoryDao.deleteInventoryItem(itemId);
	}

}
