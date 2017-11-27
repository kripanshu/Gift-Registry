package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.Inventory;
import wpl.spring.entity.RegistryItem;

public interface RegistryItemDao {

	public void addRegistryItem(RegistryItem ri);

	public void updateRegistryItem(RegistryItem update);

	public void deleteRegistryItem(RegistryItem remove);
	
	public RegistryItem getRegistryItem(int registryId, int itemId);
	
	public List<RegistryItem> getAllRegistryItem();

	public List<Inventory> searchRegistryItem(Inventory search);

}
