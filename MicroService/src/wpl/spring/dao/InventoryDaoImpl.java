package wpl.spring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.Inventory;

@Repository
public class InventoryDaoImpl implements InventoryDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public void addInventryItem(Inventory inventory) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(inventory);

	}

	@Override
	public Inventory getInventoryItem(int itemId) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM inventory WHERE ItemId = " +itemId;
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<Inventory> itemReturned = query.getResultList();
		if(itemReturned.size()>0)
			return itemReturned.get(0);
		else
			return null;
	}

	@Override
	public List<Inventory> getInventory() {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM inventory";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<Inventory> itemList = query.getResultList();
		if(itemList.size()>0)
			return itemList;
		else
			return null;
	}

	@Override
	public int updateInventoryItem(Inventory inventory) {
		
		Session currentSession = sessionFactory.getCurrentSession();
    	
		System.out.println(inventory.getItemId());
	    
		String stringQuery = "UPDATE inventory SET ItemName = :itemName, Category = :category , Description = :description , Price = :price , Quantity= :quantity WHERE ItemId="+inventory.getItemId();
	    
		Query query = currentSession.createQuery(stringQuery);
	    
	    query.setParameter("itemName", inventory.getItemName());
	    query.setParameter("category", inventory.getCategory());
	    query.setParameter("description", inventory.getDescription());
	    query.setParameter("price", inventory.getPrice());
	    query.setParameter("quantity", inventory.getQuantity());
	    return query.executeUpdate();
	}

	@Override
	public int deleteInventoryItem(int itemId) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "DELETE from inventory WHERE ItemID= "+ itemId;
	    Query query = currentSession.createQuery(stringQuery);
	    return query.executeUpdate();
	}

}
