package wpl.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.SharedRegistryDao;
import wpl.spring.entity.SharedRegistry;

@Service
public class SharedRegistryServiceImpl implements SharedRegistryService {

	@Autowired
	private SharedRegistryDao sharedRegistryDao;
	
	@Override
	@Transactional
	public void addToShared(SharedRegistry sharedRegistry) {
		sharedRegistryDao.addToShared(sharedRegistry);

	}

	@Override
	@Transactional
	public void addAutomated(String Email, String registryUrl) {
		sharedRegistryDao.addAutomated(Email, registryUrl);

	}

	@Override
	@Transactional
	public List<SharedRegistry> getSharedWithUser(String email) {
		return sharedRegistryDao.getSharedWithUser(email);
	}

}
