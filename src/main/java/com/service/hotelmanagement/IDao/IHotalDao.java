package com.service.hotelmanagement.IDao;

import java.util.Date;
import java.util.List;

import com.service.hotelmanagement.Entity.Hotal;

public interface IHotalDao {

	List<Hotal> getAll();
	
	Hotal getById(int id);
	
	public boolean exitsHotal(String name);
	void addHotal(Hotal hotal);
	
	void updateHotal(Hotal hotal);
	
	public boolean DeleteHotal(int id);
	
	public Hotal getHotalByName(String name);
	
	public List<Hotal> getHotalByDateAndCity(String city, Date date);
	
	public void indexHotal();
	
	public List<Hotal> searchBrand(String keyword);
	
	public List<Hotal> searchHotalByNumberOfStar(int numberOfStars);
	
	public boolean checkWifi(boolean wifi, String name);
	
	
}
