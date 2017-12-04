package wpl.spring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.Registry;

@Repository
public class RegistryDaoImpl implements RegistryDao {
	@Autowired 
	private SessionFactory sessionFactory;


	@Override
	public void addRegistry(Registry registry) {
		Session currentSession = sessionFactory.getCurrentSession();
		// add item
		currentSession.save(registry);
		
		// send reg forward to add to user reg table url, Share to all
		
	}


	@Override
	public Registry getRegistry(String registryUrl) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM registry WHERE RegistryUrl = '" + registryUrl+"'";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<Registry> userReturned = query.getResultList();
		if(userReturned.size()>0)
			return userReturned.get(0);
		else
			return null;
	}


	@Override
	public List<Registry> getUserRegistry(String userEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM registry where UserEmail='"+userEmail+"'";
		Query query = currentSession.createQuery(stringQuery);
		
		@SuppressWarnings("unchecked")
		
		List<Registry> userReturned = query.getResultList();
		System.out.println(userReturned);
		if(userReturned.size()>0)
			return userReturned;
		else
			return null;
	}


	@Override
	public int updateRegistry(Registry registry ) {
		Session currentSession = sessionFactory.getCurrentSession();
    	System.out.println(registry.getRegistryUrl());
	    String stringQuery = "UPDATE registry SET RegistryName= :registryName, EventDate= :eventDate, Share= :share, Address= :address WHERE RegistryUrl= '"+registry.getRegistryUrl()+"'";
	    
	    Query query = currentSession.createQuery(stringQuery);
	    
	    query.setParameter("registryName", registry.getRegistryName());
	    query.setParameter("eventDate", registry.getEventDate());
	    query.setParameter("share", registry.getShare());
	    query.setParameter("address", registry.getAddressId());
	    
	    return query.executeUpdate();
	}


	@Override
	public int deleteRegistry(String registryUrl) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "DELETE from registry WHERE RegistryUrl= '"+registryUrl+"'";
	    Query query = currentSession.createQuery(stringQuery);
	    return query.executeUpdate();
	}


	@Override
	public boolean urlAvailability(String registryUrl) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM registry WHERE RegistryUrl = '" + registryUrl+"'";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<Registry> userReturned = query.getResultList();
		if(userReturned.size()>0)
			return false;
		else
			return true;
	}

}
