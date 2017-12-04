package wpl.spring.service;

import java.util.List;

import wpl.spring.entity.RegistryItem;

public interface RegistryItemService {

	public void addRegistryItem(RegistryItem ri) ;
	
	public int updateRegistryItem(RegistryItem update);
	
	public int deleteRegistryItem(RegistryItem delete);
	
	public RegistryItem getRegistryItem(String registryUrl, int itemId);
	
	public List<RegistryItem> getRegistryItems(String registryUrl);
	
	public int selfAssign(RegistryItem item, String email);
		

}
