package com.service.hotelmanagement.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.Entity.Review;
import com.service.hotelmanagement.IDao.IHotalDao;
import com.service.hotelmanagement.IService.IHotalService;
import com.service.hotelmanagement.IService.IReviewService;
import com.service.hotelmanagement.ServiceConstants.ServiceConstant;

@RestController
@RequestMapping("api/hotal")
public class HotalController {

	@Autowired
	private IHotalService hotalService;

	@Autowired
	private IReviewService reviewService;

	@GetMapping("getall")
	public ResponseEntity<List<Hotal>> getAll() {
		List<Hotal> hotalList = hotalService.getAll();
		return new ResponseEntity<List<Hotal>>(hotalList, HttpStatus.OK);
	}

	// Show rating for a hotel and user comments/reviews.
	@GetMapping("getallreview")
	public ResponseEntity<List<Hotal>> getAllHotalReviewAndComents() {
		List<Hotal> hotalList = hotalService.getAll();
		for (int i = 0; i < hotalList.size(); i++) {
			int id = hotalList.get(i).getId();
			List<Review> reviewsList = reviewService.getByHotalId(id);
			hotalList.get(i).setReviewList(reviewsList);
		}
		return new ResponseEntity<List<Hotal>>(hotalList, HttpStatus.OK);
	}

	// Search by number of rooms required/number of travelers

	@GetMapping("searchby/numberofroom/{numberOfRoom}/{numberoftravelers}")
	public ResponseEntity<List<Hotal>> searchByNumberOfTravelers(@PathVariable("numberOfRoom") int numberOfRoom,
			@PathVariable("numberoftravelers") int numberoftravelers) {
		List<Hotal> hotalList = hotalService.getAll();
		List<Hotal> newStoreHotal = new ArrayList<Hotal>();
		for (int i = 0; i < hotalList.size(); i++) {
			int availableRoom = hotalList.get(i).getAvailableRoom();
			if (availableRoom >= numberOfRoom) {
				newStoreHotal.add(hotalList.get(i));
			}
		}
		return new ResponseEntity<List<Hotal>>(newStoreHotal, HttpStatus.OK);
	}

// Show sorted hotels based on the rating score, relevance or the cost of stay.
	@GetMapping("searchby/numberofstar/{numberOfStars}")
	public ResponseEntity<?> searchHotalByNumberOfStar(@PathVariable("numberOfStars") int numberOfStars) {
		Hotal hotal = new Hotal();
		List<Hotal> hotalList = hotalService.searchHotalByNumberOfStar(numberOfStars);
		for (int i = 0; i < hotalList.size(); i++) {
			for (int j = i + 1; j < hotalList.size(); j++) {
				int numberOfCost = hotalList.get(j).getCostOfSingleRoom();
				int newnNmberOfCost = hotalList.get(i).getCostOfSingleRoom();
				int rating = hotalList.get(i).getRating();
				int newRating = hotalList.get(j).getRating();
				if (numberOfCost < newnNmberOfCost && rating > newRating) {
					hotal = hotalList.get(i);
					hotalList.set(i, hotalList.get(j));
					hotalList.set(j, hotalList.get(i));
				}

			}

		}

		return ResponseEntity.ok().body(hotalList);
	}

	// Search hotels by city and date (to check availability)
	@SuppressWarnings("unchecked")
	@PostMapping("/search")
	public ResponseEntity<?> searchHotalInformationByCity(@RequestBody Map<String, String> json) throws ParseException {
		String city = json.get(ServiceConstant.CITY);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(json.get(ServiceConstant.DATE));
		List<Hotal> hotalList = (List<Hotal>) hotalService.getHotalByDateAndCity(city, date);
		return new ResponseEntity<List<Hotal>>(hotalList, HttpStatus.OK);

	}

	// Insert hotal information.
	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createHotalInformation(@Valid @RequestBody Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.NAME) && null != json.get(ServiceConstant.CITY)) {
			String city = json.get(ServiceConstant.CITY), name = json.get(ServiceConstant.NAME);
			Hotal hotal = new Hotal();
			hotal.setActive(Boolean.TRUE);
			hotal.setCity(city);
			hotal.setCreatedOn(new Date());
			hotal.setDeleted(Boolean.FALSE);
			hotal.setDate(new Date());
			hotal.setName(name);
			hotal.setNumberOfStars(Integer.parseInt(json.get(ServiceConstant.NUMBER_OF_STAR)));
			hotal.setCostOfSingleRoom(Integer.parseInt(json.get(ServiceConstant.COST_OF_SINGLE_ROOM)));
			hotal.setRestaurant(Boolean.parseBoolean(json.get(ServiceConstant.RESTAURANT)));
			hotal.setTotalRoom(Integer.parseInt(json.get(ServiceConstant.TOTAL_ROOM)));
			hotal.setWifi(Boolean.parseBoolean(json.get(ServiceConstant.WIFI)));
			hotal.setAirCondition(Boolean.parseBoolean(json.get(ServiceConstant.AIR_CONDITION)));
			boolean check = hotalService.exitsHotal(hotal.getName());
			if (check) {
				response.put("Ststus", Boolean.FALSE.toString());
				response.put("Description", "This Hotal All ready created");
			} else {
				hotalService.createHotal(hotal);
				response.put("Ststus", Boolean.TRUE.toString());
				response.put("Description", "This Hotal is created successfully");
			}

		}
		return ResponseEntity.ok().body(response);

	}

	// Update hotal information by id.
	@PutMapping("/update")
	ResponseEntity<Map<String, String>> updateHotal(@RequestBody Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.ID)
				&& null != hotalService.getById(Integer.parseInt(json.get(ServiceConstant.ID)))) {
			Hotal hotal = hotalService.getById(Integer.parseInt(json.get(ServiceConstant.ID)));

			if (null != json.get(ServiceConstant.AIR_CONDITION)) {
				hotal.setAirCondition(Boolean.parseBoolean(json.get(ServiceConstant.AIR_CONDITION)));
			}

			if (null != json.get(ServiceConstant.CITY)) {
				hotal.setCity(json.get(ServiceConstant.CITY));
			}

			if (null != json.get(ServiceConstant.COST_OF_STAY)) {
				hotal.setCostOfStay(Integer.parseInt(json.get(ServiceConstant.COST_OF_STAY)));
			}

			if (null != json.get(ServiceConstant.DATE)) {
				hotal.setDate(new Date());
			}

			if (null != json.get(ServiceConstant.NAME)) {
				hotal.setName(json.get(ServiceConstant.NAME));
			}

			if (null != json.get(ServiceConstant.NUMBER_OF_STAR)) {
				hotal.setNumberOfStars(Integer.parseInt(json.get(ServiceConstant.NUMBER_OF_STAR)));
			}

			if (null != json.get(ServiceConstant.RESTAURANT)) {
				hotal.setRestaurant(Boolean.parseBoolean(json.get(ServiceConstant.RESTAURANT)));
			}

			if (null != json.get(ServiceConstant.TOTAL_ROOM)) {
				hotal.setTotalRoom(Integer.parseInt(json.get(ServiceConstant.TOTAL_ROOM)));
			}
			boolean update = hotalService.updateHotal(hotal);
			if (update) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Information update");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Description", "Information not update");
			}
		}
		return ResponseEntity.ok().body(response);

	}

	// Delete hotal information by id.
	@DeleteMapping("/delete/{id}")
	ResponseEntity<Map<String, String>> deleteHotalInformation(@PathVariable("id") int id) {
		Map<String, String> response = new HashMap<String, String>();
		Hotal hotal = hotalService.getById(id);
		if (null != hotal) {
			boolean deleteInformation = hotalService.DeleteHotal(id);
			if (deleteInformation) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your information is deleted successfully");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Description", "Your information is not deleted");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Description", "Hotal does not exist with given Hotal Id");
		}
		return ResponseEntity.ok().body(response);

	}

}
