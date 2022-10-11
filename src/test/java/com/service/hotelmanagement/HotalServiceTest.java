package com.service.hotelmanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.hotelmanagement.Controller.HotalController;
import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.IDao.IHotalDao;
import com.service.hotelmanagement.IDao.IUserDao;
import com.service.hotelmanagement.IService.IHotalService;
import com.service.hotelmanagement.IService.IUserService;

//@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { HotalServiceTest.class })
public class HotalServiceTest {


	@Mock
	private IHotalService hotalService;
	
	//public List<Hotal> myHotalData ;
	@InjectMocks
	HotalController hotalController;
	List<Hotal> myHotalData = new ArrayList<Hotal>();
	
	@Test
	@Order(2)
	public void test_getAllHotalInformation() {
		myHotalData.add(new Hotal(1,"Raju Hotal", "Patna"));
//		myHotalData.add(new Hotal(1,"Rohan Hotel", "Delhi", 300,3, true, false, true,223,22-12-2022,false,50,"V.good"));
//		myHotalData.add(new Hotal(1,"Sohan Hotal", "Muzaffarpur", 20,5, true, false, true,223,23-12-2022,true,50,"good"));
//		myHotalData.add(new Hotal(1,"Rama Hotal", "Patna", 40,5, true, false, true,254,26-12-2022,false,50,"V.good"));
		when(hotalService.getAll()).thenReturn(myHotalData);
	ResponseEntity<List<Hotal>>	 response = hotalController.getAll();
		
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(2,myHotalData.size());

		System.out.println(myHotalData);
	}
	
    @Test
	@Order(3)
	public void HotalServiceTest_test() {
		System.out.println("hii");
	}

}
