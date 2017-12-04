package wpl.spring.service;

import java.util.List;

import wpl.spring.entity.SharedRegistry;

public interface SharedRegistryService {
	
	public void addToShared(SharedRegistry sharedRegistry);
	public void addAutomated(String Email, String registryUrl);
	public List<SharedRegistry> getSharedWithUser(String email);

}
