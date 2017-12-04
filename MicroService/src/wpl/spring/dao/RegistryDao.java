package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.Registry;

public interface RegistryDao {
	
	public void addRegistry(Registry registry);
	
	public Registry getRegistry(String registryUrl);
	
	public List<Registry> getUserRegistry(String userEmail);
	
	public int updateRegistry(Registry registry);
	
	public int deleteRegistry(String registryUrl);
		
	public boolean urlAvailability(String registryUrl);

}
