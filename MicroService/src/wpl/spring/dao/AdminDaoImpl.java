package wpl.spring.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.User;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String authenticateAdmin(String email, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "FROM admin WHERE Email = '" + email+"' and Password = '" + password+"'";
		Query query = currentSession.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<User> adminReturned = query.getResultList();
		if(adminReturned.size()>0)
			return email;
		else
			return null;
	}
	
	

}
