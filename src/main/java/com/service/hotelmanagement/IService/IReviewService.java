package com.service.hotelmanagement.IService;

import java.util.List;

import com.service.hotelmanagement.Entity.Review;

public interface IReviewService {
	
	public boolean createReview(Review review);
	
	public Review getById(int id);
	
	public List<Review> getByHotalId(int hotalId);
	
	public boolean upDateReview(Review review);
	
	public boolean deleteReview(int id);

}
