package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.RegistryItem;

public interface RegistryItemDao {

	public void addRegistryItem(RegistryItem ri);

	public int updateRegistryItem(RegistryItem update);

	public int deleteRegistryItem(RegistryItem remove);
	
	public RegistryItem getRegistryItem(String registryUrl, int itemId);
	
	public List<RegistryItem> getRegistryItems(String registryUrl);
	
	public int selfAssign(RegistryItem item, String email);


}
