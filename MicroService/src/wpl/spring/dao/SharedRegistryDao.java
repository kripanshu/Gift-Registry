package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.SharedRegistry;

public interface SharedRegistryDao {

	public void addToShared(SharedRegistry sharedRegistry);
	public void addAutomated(String Email, String registryUrl);
	public List<SharedRegistry> getSharedWithUser(String email);
}
