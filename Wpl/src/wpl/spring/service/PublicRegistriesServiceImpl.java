package wpl.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.PublicRegistriesDao;
import wpl.spring.entity.Registry;

@Service
public class PublicRegistriesServiceImpl implements PublicRegistriesService {

	//Inject Dao dependency
	@Autowired
	private PublicRegistriesDao publicRegistriesDao;
	
	
	//Delegate call to Dao
	@Override
	@Transactional
	public List<Registry> getPublicRegistries() {
		return publicRegistriesDao.getPublicRegistries();
	}

}
