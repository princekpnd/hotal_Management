package com.service.hotelmanagement.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.Entity.Review;
import com.service.hotelmanagement.IDao.IReviewDao;

@Transactional
@Repository
public class ReviewDaoImpl  implements IReviewDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public ReviewDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createReview(Review review) {
		entityManager.persist(review);
		
	}

	@Override
	public Review getById(int id) {
	Review review = entityManager.createNamedQuery("Review.getById", Review.class).setParameter("id", id).getSingleResult();
		return review;
	}

	@Override
	public void upDateReview(Review review) {
		entityManager.merge(review);
		
	}



	@Override
	public boolean deleteReview(int id) {
		Query query = entityManager.createQuery("delete Review where id = " + id);
		query.executeUpdate();
		entityManager.flush();
		return true;
	}

	@Override
	public List<Review> getByHotalId(int hotalId) {
		List<Review> reviewList = entityManager.createNamedQuery("Review. getByHotalId", Review.class).setParameter("hotalId", hotalId).getResultList();
		return reviewList;
	}

//	@Override
//	public void deleteReview(int id) {
//		Query query = entityManager.createQuery("delete Review where id = " + id);
//		query.executeUpdate();
//		entityManager.flush();
//		return true;
//		
//	}

}
