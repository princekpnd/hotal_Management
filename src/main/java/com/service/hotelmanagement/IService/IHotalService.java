package com.service.hotelmanagement.IService;

import java.util.Date;
import java.util.List;

import com.service.hotelmanagement.Entity.Hotal;

public interface IHotalService {
	
	List<Hotal> getAll();
	
	Hotal getById(int id);
	
	public boolean exitsHotal(String name);
	
	public boolean createHotal(Hotal hotal);
	
	public boolean updateHotal(Hotal hotal);
	
	public boolean DeleteHotal(int id);
	
	public Hotal getHotalByName(String name);
	
	public List<Hotal> searchHotalByNumberOfStar(int numberOfStars);
	
	public List<Hotal> getHotalByDateAndCity(String city, Date date);
	
	public boolean checkWifi(boolean wifi, String name);
	
	

}
