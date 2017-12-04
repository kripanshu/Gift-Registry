package wpl.spring.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired 
	private SessionFactory sessionFactory;
	  
	  	@Override
	    public int updateUser(User user) {

	    	Session currentSession = sessionFactory.getCurrentSession();
	    	System.out.println(user.getFirstName());
	    	
		    String stringQuery = "UPDATE User SET Fname= :firstname, Lname= :lastname, SecurityQuestion= :securityquestion, "
		    		+ "SecurityAnswer= :securityanswer, Password= :password  WHERE Email= '"+user.getEmail()+"'";
		    
		    Query query = currentSession.createQuery(stringQuery);
		    
		    query.setParameter("firstname", user.getFirstName());
		    query.setParameter("lastname", user.getLastName());
		    query.setParameter("securityquestion", user.getSecurityQuestion());
		    query.setParameter("securityanswer", user.getSecurityAnswer());
		    query.setParameter("password", user.getPassword());
		    return query.executeUpdate();		    
	    }

		@Override
		public void addUser(User user) {
			
			Session currentSession =sessionFactory.getCurrentSession();
			currentSession.save(user);
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<User> getAllUsers() {
			
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM User";
			Query query = currentSession.createQuery(stringQuery);
			List<User> userList = query.getResultList();
			if(userList.size()>0)
				return userList;
			else
				return null;
		}

		@Override
		public User getUser(String user) {
			// TODO Auto-generated method stub
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM User WHERE Email = '" + user+"'";
			Query query = currentSession.createQuery(stringQuery);
			@SuppressWarnings("unchecked")
			List<User> userReturned = query.getResultList();
			if(userReturned.size()>0)
				return userReturned.get(0);
			else
				return null;
		}

		@Override
		public int deleteUser(String email) {
			Session currentSession = sessionFactory.getCurrentSession();
	    	  	
		    String stringQuery = "DELETE FROM User WHERE Email='"+ email+"'";
		    
		    Query query = currentSession.createQuery(stringQuery);
		    
		    return query.executeUpdate();
		}

		@Override
		public String authenticateUser(String email, String password) {
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM User WHERE Email = '" + email+"' and Password = '" + password+"'";
			Query query = currentSession.createQuery(stringQuery);
			@SuppressWarnings("unchecked")
			List<User> userReturned = query.getResultList();
			if(userReturned.size()>0)
				return email;
			else
				return null;
		}
	    
		
	}
