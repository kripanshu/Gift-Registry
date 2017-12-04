package wpl.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.UserDao;
import wpl.spring.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);

	}

	@Override
	@Transactional
	public int updateUser(User update) {
		return userDao.updateUser(update);

	}


	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	@Transactional
	public User getUser(String email) {
		return userDao.getUser(email);
	}

	@Override
	@Transactional
	public int deleteUser(String email) {
		return userDao.deleteUser(email);
	}

	@Override
	@Transactional
	public String authenticateUser(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.authenticateUser(email, password);
	}

	@Override
	@Transactional
	public boolean emailAvailability(String email) {
		return userDao.emailAvailability(email);
	}

}
