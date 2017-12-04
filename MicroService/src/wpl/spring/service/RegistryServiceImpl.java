package wpl.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wpl.spring.dao.RegistryDao;
import wpl.spring.entity.Registry;

@Service
public class RegistryServiceImpl implements RegistryService {
	
	@Autowired
	private RegistryDao registryDao;

	@Override
	@Transactional
	public void addRegistry(Registry registry) {
		registryDao.addRegistry(registry);
		
	}

	@Override
	@Transactional
	public Registry getRegistry(String registryUrl) {
		return registryDao.getRegistry(registryUrl);
	}

	@Override
	@Transactional
	public List<Registry> getUserRegistry(String userEmail) {
		return registryDao.getUserRegistry(userEmail);
	}

	@Override
	@Transactional
	public int updateRegistry(Registry registry) {
		return registryDao.updateRegistry(registry);
	}

	@Override
	@Transactional
	public int deleteRegistry(String registryUrl) {
		return registryDao.deleteRegistry(registryUrl);
	}

	@Override
	@Transactional
	public boolean urlAvailability(String registryUrl) {
		return registryDao.urlAvailability(registryUrl);
	}

}
