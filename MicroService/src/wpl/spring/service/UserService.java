package wpl.spring.service;

import java.util.List;


import wpl.spring.entity.User;


public interface UserService {
	public void addUser(User user) ;
	public int updateUser(User update);
	public int deleteUser(String email);
	public String authenticateUser(String email, String password);
	public List<User> getAllUsers();
	public User getUser(String email);
	public boolean emailAvailability(String email); 

}
