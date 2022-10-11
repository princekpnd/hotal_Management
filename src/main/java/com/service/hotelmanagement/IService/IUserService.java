package com.service.hotelmanagement.IService;

import com.service.hotelmanagement.Entity.User;

public interface IUserService {
	
	public User getByUserId(int id);
	
	public boolean exitsUser(String firstName, String mobileNumber);
	public boolean createUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int id);
	
	public User getByMobileNumber( String mobileNumber);

}
