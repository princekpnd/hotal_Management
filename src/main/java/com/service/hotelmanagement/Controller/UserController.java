package com.service.hotelmanagement.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.service.hotelmanagement.Entity.User;
import com.service.hotelmanagement.IService.IHotalService;
import com.service.hotelmanagement.IService.IUserService;
import com.service.hotelmanagement.ServiceConstants.ServiceConstant;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IHotalService hotalService;

	@GetMapping("get/{id}")
	public ResponseEntity<User> getByUserId(@PathVariable("id") int id) {
		User user = userService.getByUserId(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("getbymobilenumber/{mobileNumber}")
	public ResponseEntity<User> getByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) {
		User user = userService.getByMobileNumber(mobileNumber);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//Search hotels by city and date (to check availability).
	@SuppressWarnings("unchecked")
	@PostMapping("searchTest/{numberOfRoom")
	public ResponseEntity<List<Hotal>> searchHotalInformationByCityAndDate(@RequestBody Map<String, String> json,
			@PathVariable("numberOfRoom") int numberOfRoom) throws ParseException {
		String city = json.get(ServiceConstant.CITY);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(json.get(ServiceConstant.DATE));
		List<Hotal> hotalList = (List<Hotal>) hotalService.getHotalByDateAndCity(city, date);
		ArrayList<Hotal> newHotal = new ArrayList<Hotal>();
		for (int i = 0; i < hotalList.size(); i++) {
			int availableRoom = hotalList.get(i).getAvailableRoom();
			if (availableRoom >= numberOfRoom) {
				newHotal.add(hotalList.get(i));

			}
		}
		return new ResponseEntity<List<Hotal>>(newHotal, HttpStatus.OK);
	}
	
	
//This API is use for book hotal if hotal available room is greater than number of search room.

	@GetMapping("bookhotal/{hotalName}/{numberOfRoom}")
	public ResponseEntity<Map<String, String>> BookHotalByUser(@PathVariable("hotalName") String hotalName,
			@PathVariable("numberOfRoom") int numberOfRoom) {
		Map<String, String> response = new HashMap<String, String>();
		Hotal hotal = hotalService.getHotalByName(hotalName);
		if (hotal.getNumberOfBookedRoom() == 0) {
			hotal.setAvailableRoom(hotal.getTotalRoom());
			hotalService.updateHotal(hotal);
		}
		if (hotal.getAvailableRoom() >= numberOfRoom) {
			hotal.setNumberOfBookedRoom(hotal.getNumberOfBookedRoom() + numberOfRoom);
			hotal.setAvailableRoom(hotal.getTotalRoom() - hotal.getNumberOfBookedRoom());
			hotalService.updateHotal(hotal);
			response.put("Status", Boolean.TRUE.toString());
			response.put("Description", "Your room is booked successfully");
		} else {
			response.put("Status", Boolean.FALSE.toString());
			response.put("Description", "Room is not available in this hotal");
		}
		return ResponseEntity.ok().body(response);
	}

// This API is use for INSERT user information

	@PostMapping("/create")
	ResponseEntity<Map<String, String>> createUserInformation(Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.FIRST_NAME) && null != json.get(ServiceConstant.MOBILE_NUMBER)) {
			String name = json.get(ServiceConstant.FIRST_NAME), mobileNumber = json.get(ServiceConstant.MOBILE_NUMBER);
			User user = new User();
			user.setAadharNumber(json.get(ServiceConstant.AADHAR_NUMBER));
			user.setActive(Boolean.TRUE);
			user.setCreatedOn(new Date());
			user.setDeleted(Boolean.FALSE);
			user.setHotalId(Integer.parseInt(json.get(ServiceConstant.HOTAL_ID)));
			user.setLastName(json.get(ServiceConstant.LAST_NAME));
			user.setHotalName(json.get(ServiceConstant.HOTAL_NAME));
			user.setMobileNumber(mobileNumber);

			boolean check = userService.exitsUser(user.getFirstName(), user.getMobileNumber());
			if (check) {
				response.put("status", Boolean.FALSE.toString());
				response.put("description", "You are all ready ragistared");
			} else {
				userService.createUser(user);
				response.put("status", Boolean.TRUE.toString());
				response.put("description", "Your information is created");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Description", "Please enter name and mobile number");
		}

		return ResponseEntity.ok().body(response);

	}

	// This API is use for UPDATE user information using userId.

	@PutMapping("/update")
	ResponseEntity<Map<String, String>> updateUserInformation(Map<String, String> json) {
		Map<String, String> response = new HashMap<String, String>();
		if (null != json.get(ServiceConstant.ID)
				&& null != userService.getByUserId(Integer.parseInt(json.get(ServiceConstant.ID)))) {
			User user = userService.getByUserId(Integer.parseInt(json.get(ServiceConstant.ID)));

			if (null != json.get(ServiceConstant.AADHAR_NUMBER)) {
				user.setAadharNumber(json.get(ServiceConstant.AADHAR_NUMBER));
			}

			if (null != json.get(ServiceConstant.FIRST_NAME)) {
				user.setFirstName(json.get(ServiceConstant.FIRST_NAME));
			}

			if (null != json.get(ServiceConstant.LAST_NAME)) {
				user.setLastName(json.get(ServiceConstant.LAST_NAME));
			}

			if (null != json.get(ServiceConstant.MOBILE_NUMBER)) {
				user.setMobileNumber(json.get(ServiceConstant.MOBILE_NUMBER));
			}
			boolean update = userService.updateUser(user);
			if (update) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "Your information has been updated");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Description", "Your information is not updated");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Description", "Please enter userId");
		}
		return ResponseEntity.ok().body(response);

	}
	// This API is use for DELETE user information by user Id.

	@DeleteMapping("delete/{userId}")
	ResponseEntity<Map<String, String>> deleteUserInformation(@PathVariable("id") int id) {
		Map<String, String> response = new HashMap<String, String>();
		User user = userService.getByUserId(id);
		if (null != user) {
			boolean deleteUser = userService.deleteUser(id);
			if (deleteUser) {
				response.put("status", Boolean.TRUE.toString());
				response.put("Description", "User has been deleted successfully");
			} else {
				response.put("status", Boolean.FALSE.toString());
				response.put("Description", "User has not been deleted successfully");
			}
		} else {
			response.put("status", Boolean.FALSE.toString());
			response.put("Description", "Please enter valid user id");
		}
		return ResponseEntity.ok().body(response);
	}

}
