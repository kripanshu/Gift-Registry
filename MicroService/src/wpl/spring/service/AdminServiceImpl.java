package wpl.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wpl.spring.dao.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional
	public String authenticateAdmin(String email, String password) {
		return adminDao.authenticateAdmin(email, password);
	}

}
