package wpl.spring.dao;

import java.util.List;

import wpl.spring.entity.User;

public interface UserDao {

		public void addUser(User user);
		public int updateUser(User update);
		public int deleteUser(String email);
		public List<User> getAllUsers();	
		public User getUser(String email);
		public String authenticateUser(String email, String password);
		
}
