package wpl.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.RegistryItemDao;
import wpl.spring.entity.Inventory;
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
	public void updateRegistryItem(RegistryItem update) {
		addItemDao.updateRegistryItem(update);
		
	}

	@Override
	@Transactional
	public void deleteRegistryItem(RegistryItem remove) {
		addItemDao.deleteRegistryItem(remove);
		
	}

	@Override
	@Transactional
	public List<Inventory> searchRegistryItem(Inventory search) {
		return addItemDao.searchRegistryItem(search);
		
	}

	@Override
	@Transactional
	public RegistryItem getRegistryItem(int registryId, int itemId) {
		return addItemDao.getRegistryItem(registryId, itemId);
	}

	@Override
	@Transactional
	public List<RegistryItem> getAllRegistryItem() {
		return addItemDao.getAllRegistryItem();
	}
	
}
