package com.service.hotelmanagement.IDao;

import java.util.List;

import com.service.hotelmanagement.Entity.Review;

public interface IReviewDao {
 void createReview(Review review);
 
 public Review getById(int id);
 
void upDateReview(Review review);
 
 public boolean deleteReview(int id);
 
 List<Review> getByHotalId(int hotalId);
 
}
