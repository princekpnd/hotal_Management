package com.service.hotelmanagement.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.service.hotelmanagement.Entity.User;
import com.service.hotelmanagement.IDao.IUserDao;

@Transactional
@Repository
public class UserDaoImpl  implements IUserDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getByUserId(int id) {
		User user = entityManager.createNamedQuery("User.getById", User.class).setParameter("id", id).getResultList().stream().findFirst()
				.orElse(null);
		return null != user ? user : null;
		
		
//		User user = entityManager.createNamedQuery("User.getById", User.class).setParameter("id", id).getSingleResult();
//				
//		return user;
	}

	@Override
	public boolean exitsUser(String firstName, String mobileNumber) {
	User user = entityManager.createNamedQuery("User.checkUser", User.class).setParameter("firstName", firstName).setParameter("mobileNumber", mobileNumber).getResultList().stream().findFirst().orElse(null);;
	return null != user ?Boolean.TRUE:Boolean.FALSE;
	
	
	}

	@Override
	public void addUser(User user) {
	entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
	entityManager.merge(user);
		
	}

	@Override
	public boolean deleteUser(int id) {
		Query query = entityManager.createQuery("delete User where id = " + id);
		query.executeUpdate();
		entityManager.flush();
		return true;
	}

	@Override
	public User getByMobileNumber(String mobileNumber) {
	User user = entityManager.createNamedQuery("User.getByMobileNumber", User.class).setParameter("mobileNumber", mobileNumber).getSingleResult();
		return user;
	}

}
