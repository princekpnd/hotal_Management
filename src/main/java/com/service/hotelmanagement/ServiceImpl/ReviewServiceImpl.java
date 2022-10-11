package com.service.hotelmanagement.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.hotelmanagement.Entity.Review;
import com.service.hotelmanagement.IDao.IReviewDao;
import com.service.hotelmanagement.IService.IReviewService;


@Service
public class ReviewServiceImpl  implements IReviewService{

	@Autowired
	private IReviewDao reviewDao;
	
	public ReviewServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createReview(Review review) {
	reviewDao.createReview(review);
		return true;
	}

	@Override
	public Review getById(int id) {
		return reviewDao.getById(id);
	}

	@Override
	public boolean upDateReview(Review review) {
		 reviewDao.upDateReview(review);
		 return true;
	}

	@Override
	public boolean deleteReview(int id) {
		return	reviewDao.deleteReview(id);
	}

	@Override
	public List<Review> getByHotalId(int hotalId) {
		return reviewDao.getByHotalId(hotalId);
	}

}
