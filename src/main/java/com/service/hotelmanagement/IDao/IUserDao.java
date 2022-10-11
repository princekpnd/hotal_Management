package com.service.hotelmanagement.IDao;

import com.service.hotelmanagement.Entity.User;

public interface IUserDao {
public User getByUserId(int id);

public boolean exitsUser(String firstName, String mobileNumber);
void addUser(User user);

void updateUser(User user);

public boolean deleteUser(int id);

public User getByMobileNumber(String mobileNumber);
}
