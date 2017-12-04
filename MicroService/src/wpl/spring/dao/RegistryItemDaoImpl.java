package wpl.spring.dao;

import java.util.List;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.RegistryItem;

@Repository
public class RegistryItemDaoImpl implements RegistryItemDao {

	//Inject session factory
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public void addRegistryItem(RegistryItem ri) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// add item
		currentSession.save(ri);
		
	}

	@Override
	public int updateRegistryItem(RegistryItem update) {
		
		Session currentSession = sessionFactory.getCurrentSession();
    	System.out.println(update.getRegistryUrl());
	    String stringQuery = "UPDATE registryitem SET Quantity= :quantity WHERE RegistryUrl= '"+update.getRegistryUrl()+"' AND ItemId = "+update.getItemId();
	    Query query = currentSession.createQuery(stringQuery);
	    query.setParameter("quantity", update.getQuantity());
	    return query.executeUpdate();
	    
		
	}

	@Override
	public int deleteRegistryItem(RegistryItem remove) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "DELETE from registryitem WHERE RegistryUrl= '"+remove.getRegistryUrl()+"' AND ItemId = '"+remove.getItemId()+"'";
	    Query query = currentSession.createQuery(stringQuery);
	    return query.executeUpdate();
		
	}

	
	

	@Override
	public RegistryItem getRegistryItem(String registryUrl, int itemId) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM registryitem WHERE RegistryUrl = '" + registryUrl + "' and ItemId = " + itemId;
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<RegistryItem> userReturned = query.getResultList();
		if(userReturned.size()>0)
			return userReturned.get(0);
		else
			return null;
	}

	@Override
	public List<RegistryItem> getRegistryItems(String registryUrl) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM registryitem WHERE RegistryUrl ='"+registryUrl+"'";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<RegistryItem> itemReturned = query.getResultList();
		System.out.println(itemReturned);
		System.out.println("************************************" + itemReturned);
		if(itemReturned.size()>0)
			return itemReturned;
		else
			return null;
	}

	@Override
	public int selfAssign(RegistryItem item, String email) {
		Session currentSession = sessionFactory.getCurrentSession();

	    String stringQuery = "UPDATE registryitem SET Taken = :taken , Email = :email WHERE RegistryUrl= '"+item.getRegistryUrl()+"' AND ItemId = "+item.getItemId();

	    Query query = currentSession.createQuery(stringQuery);
	    
	    query.setParameter("taken", 1);
	    query.setParameter("email", email);
	    
	    return query.executeUpdate();
	}

	
}
