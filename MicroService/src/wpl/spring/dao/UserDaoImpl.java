package wpl.spring.dao;

import java.security.SecureRandom;
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
	static final String tokenString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom secureRandom = new SecureRandom();
	  
	  	@Override
	    public int updateUser(User user) {

	    	Session currentSession = sessionFactory.getCurrentSession();
	    	System.out.println(user.getFirstName());
	    	
		    String stringQuery = "UPDATE user SET Fname= :firstname, Lname= :lastname, SecurityQuestion= :securityQuestion, SecurityAnswer= :securityAnswer, Password= :password, Street = :street, State = :state, Zipcode = :zipcode, Country = :country  WHERE Email= '"+user.getEmail()+"'";
		    
		    Query query = currentSession.createQuery(stringQuery);
		    
		    query.setParameter("firstname", user.getFirstName());
		    query.setParameter("lastname", user.getLastName());
		    query.setParameter("securityQuestion", user.getSecurityQuestion());
		    query.setParameter("securityAnswer", user.getSecurityAnswer());
		    query.setParameter("password", user.getPassword());
		    query.setParameter("street", user.getStreet());
		    query.setParameter("state", user.getState());
		    query.setParameter("zipcode", user.getZipcode());
		    query.setParameter("country", user.getCountry());
		    return query.executeUpdate();		    
	    }

		@Override
		public void addUser(User user) {
			
			user.setUserToken(tokenGenerator(10));
			
			Session currentSession =sessionFactory.getCurrentSession();
			currentSession.save(user);
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<User> getAllUsers() {
			
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM user";
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
			String stringQuery = "FROM user WHERE Email = '" + user+"'";
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
	    	  	
		    String stringQuery = "DELETE FROM user WHERE Email='"+ email+"'";
		    
		    Query query = currentSession.createQuery(stringQuery);
		    
		    return query.executeUpdate();
		}

		@Override
		public String authenticateUser(String email, String password) {
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM user WHERE Email = '" + email+"' and Password = '" + password+"'";
			Query query = currentSession.createQuery(stringQuery);
			@SuppressWarnings("unchecked")
			List<User> userReturned = query.getResultList();
			User u = userReturned.get(0);
			
			if(userReturned.size()>0)
				return u.getUserToken();
			else
				return null;
		}

		@Override
		public boolean emailAvailability(String email) {
			Session currentSession = sessionFactory.getCurrentSession();
			String stringQuery = "FROM user WHERE Email = '" + email+"'";
			Query query = currentSession.createQuery(stringQuery);
			@SuppressWarnings("unchecked")
			List<User> userReturned = query.getResultList();
			if(userReturned.size()>0)
				return false;
			else
				return true;
		}
		
		String tokenGenerator( int len ){
			   StringBuilder token = new StringBuilder( len );
			   for( int i = 0; i < len; i++ ) 
			      token.append( tokenString.charAt( secureRandom.nextInt(tokenString.length())));
			   return token.toString();
			}
	    
		
	}
