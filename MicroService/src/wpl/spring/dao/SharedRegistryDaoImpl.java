package wpl.spring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.SharedRegistry;

@Repository
public class SharedRegistryDaoImpl implements SharedRegistryDao {
	
	@Autowired 
	private SessionFactory sessionFactory;

	@Override
	public void addToShared(SharedRegistry sharedRegistry) {
		Session currentSession =sessionFactory.getCurrentSession();
		currentSession.save(sharedRegistry);

	}

	@Override
	public void addAutomated(String Email, String registryUrl) {
		SharedRegistry shared = new SharedRegistry();
		shared.setEmail(Email);
		shared.setRegistryUrl(registryUrl);
		
		Session currentSession =sessionFactory.getCurrentSession();
		currentSession.save(shared);

	}

	@Override
	public List<SharedRegistry> getSharedWithUser(String email) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM sharedregistry WHERE Email =  '"+ email+"' OR Email = 'ShareToAll'";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<SharedRegistry> sharedRegistry = query.getResultList();
		System.out.println(sharedRegistry);
		if(sharedRegistry.size()>0)
			return sharedRegistry;
		else
			return null;
	}

}
