package com.service.hotelmanagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.Entity.Review;
import com.service.hotelmanagement.IService.IHotalService;
import com.service.hotelmanagement.IService.IReviewService;
import com.service.hotelmanagement.ServiceConstants.ServiceConstant;

@RestController
@RequestMapping("api/review")
public class ReviewController {

	@Autowired
	private IReviewService reviewService;

	@Autowired
	private IHotalService hotalService;

	@GetMapping("get/{id}")
	public ResponseEntity<Review> getById(@PathVariable("id") int id) {
		Review review = reviewService.getById(id);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@GetMapping("getby/{hotalId}")
	public ResponseEntity<List<Review>> getByHotalId(@PathVariable("hotalId") int hotalId){
		List<Review> reviewsList = reviewService.getByHotalId(hotalId);
		return new ResponseEntity<List<Review>>(reviewsList, HttpStatus.OK);
	}

	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createReview(@RequestBody Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.HOTAL_ID) && null != json.get(ServiceConstant.USER_ID)) {
			Review review = new Review();
			int hotalId = Integer.parseInt(json.get(ServiceConstant.HOTAL_ID));

			review.setComments(json.get(ServiceConstant.COMMENTS));
			review.setHotalId(Integer.parseInt(json.get(ServiceConstant.HOTAL_ID)));
			review.setRatings(json.get(ServiceConstant.RATINGS));
			review.setUserId(Integer.parseInt(json.get(ServiceConstant.USER_ID)));
			boolean createReview = reviewService.createReview(review);

			Hotal hotal = hotalService.getById(hotalId);
			hotal.setRating(Integer.parseInt(review.getRatings()));
			hotal.setReview(review.getComments());
			hotalService.updateHotal(hotal);

			if (createReview) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your review is created");
			} else {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your review is not created");
			}
		} else {
			response.put("status", Boolean.TRUE.toString());
			response.put("Description", "Please enter user id and hotal id");
		}

		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> updateReview(@RequestBody Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.ID)
				&& null != reviewService.getById(Integer.parseInt(json.get(ServiceConstant.ID)))) {
			Review review = new Review();
			if (null != json.get(ServiceConstant.HOTAL_ID)) {
				review.setHotalId(Integer.parseInt(json.get(ServiceConstant.HOTAL_ID)));
			}
			if (null != json.get(ServiceConstant.COMMENTS)) {
				review.setComments(json.get(ServiceConstant.COMMENTS));
			}
			if (null != json.get(ServiceConstant.RATINGS)) {
				review.setRatings(json.get(ServiceConstant.RATINGS));
			}
			boolean updateReview = reviewService.upDateReview(review);
			if (updateReview) {
				response.put("sataus", Boolean.TRUE.toString());
				response.put("Description", "Your review is update successfully");
			} else {
				response.put("sataus", Boolean.FALSE.toString());
				response.put("Description", "Your review is not update successfully");
			}
		}
		return ResponseEntity.ok().body(response);
	}

	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Map<String, String>> deleteReview(@PathVariable("id") int id) {
		Map<String, String> response = new HashMap<String, String>();
		Review review = reviewService.getById(id);
		if (null != review) {
			boolean deleteReview = reviewService.deleteReview(id);
			if (deleteReview) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your review is delete successfully");
			} else {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your review is not delete successfully");
			}
		}
		return ResponseEntity.ok().body(response);

	}
}
