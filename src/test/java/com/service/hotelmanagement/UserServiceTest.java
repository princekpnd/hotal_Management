package com.service.hotelmanagement;

import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.service.hotelmanagement.Entity.User;
import com.service.hotelmanagement.IDao.IUserDao;
import com.service.hotelmanagement.IService.IUserService;

@SpringBootTest(classes = { UserServiceTest.class })
public class UserServiceTest {

	@Mock
	private IUserDao userDao;

	@InjectMocks
	private IUserService userService;

	@Test
	@Order(1)
	public void test_userGetAll() {
//		userService.get
//		List<User> userList = new ()
	}
	public UserServiceTest() {
		// TODO Auto-generated constructor stub
	}

}
