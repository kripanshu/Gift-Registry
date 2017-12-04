package wpl.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.RegistryItemDao;
import wpl.spring.entity.RegistryItem;

@Service
public class RegistryItemServiceImp implements RegistryItemService {

	//inject additemdao 
	
	@Autowired
	private RegistryItemDao addItemDao;

	@Override
	@Transactional
	public void addRegistryItem(RegistryItem ri) {
		addItemDao.addRegistryItem(ri);
		
	}

	@Override
	@Transactional
	public int updateRegistryItem(RegistryItem update) {
		return addItemDao.updateRegistryItem(update);
		
	}

	@Override
	@Transactional
	public int deleteRegistryItem(RegistryItem remove) {
		return addItemDao.deleteRegistryItem(remove);
		
	}


	@Override
	@Transactional
	public RegistryItem getRegistryItem(String registryUrl, int itemId) {
		return addItemDao.getRegistryItem(registryUrl, itemId);
	}

	@Override
	@Transactional
	public List<RegistryItem> getRegistryItems(String registryUrl) {
		// TODO Auto-generated method stub
		return addItemDao.getRegistryItems(registryUrl);
	}

	@Override
	@Transactional
	public int selfAssign(RegistryItem item, String email) {
		return addItemDao.selfAssign(item, email);
	}

}
