package com.service.hotelmanagement.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.hotelmanagement.Entity.User;
import com.service.hotelmanagement.IDao.IUserDao;
import com.service.hotelmanagement.IService.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public User getByUserId(int id) {
		return userDao.getByUserId(id);
	}
	@Override
	public boolean exitsUser(String firstName, String mobileNumber) {
		return userDao.exitsUser(firstName, mobileNumber);
	}
	@Override
	public boolean createUser(User user) {
		if(userDao.exitsUser(user.getFirstName(), user.getMobileNumber())) {
			return false;
		}else {
			userDao.addUser(user);
			return true;
		}
		
	}
	@Override
	public boolean updateUser(User user) {
		userDao.updateUser(user);
		return true;
	}
	@Override
	public boolean deleteUser(int id) {
		userDao.deleteUser(id);
		return true;
	}
	@Override
	public User getByMobileNumber(String mobileNumber) {
		return userDao.getByMobileNumber(mobileNumber);
	}

}
