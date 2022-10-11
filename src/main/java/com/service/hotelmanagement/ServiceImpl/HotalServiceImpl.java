package com.service.hotelmanagement.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.IDao.IHotalDao;
import com.service.hotelmanagement.IService.IHotalService;

@Service
public class HotalServiceImpl implements IHotalService {
	@Autowired
	private IHotalDao hotalDao;

	public HotalServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Hotal> getAll() {
		return hotalDao.getAll();
	}

	@Override
	public Hotal getById(int id) {
		return hotalDao.getById(id);
	}

	@Override
	public boolean exitsHotal(String name) {
		return  hotalDao.exitsHotal(name);
		
	}

	@Override
	public boolean createHotal(Hotal hotal) {
		if(exitsHotal(hotal.getName())) {
			return false;
		}else {
			hotalDao.addHotal(hotal);
			return true;
		}
		
	}

	@Override
	public boolean updateHotal(Hotal hotal) {
		hotalDao.updateHotal(hotal);
		return true;
	}

	@Override
	public boolean DeleteHotal(int id) {
		return hotalDao.DeleteHotal(id);
	}

	@Override
	public Hotal getHotalByName(String name) {
		return hotalDao.getHotalByName(name);
	}

	@Override
	public List<Hotal> getHotalByDateAndCity(String city, Date date) {
		return hotalDao.getHotalByDateAndCity(city, date);
	}

	@Override
	public boolean checkWifi(boolean wifi, String name) {
		return hotalDao.checkWifi(wifi, name);
	}

	@Override
	public List<Hotal> searchHotalByNumberOfStar(int numberOfStars) {
	return hotalDao.searchHotalByNumberOfStar(numberOfStars);
	}

//	@Override
//	public void indexHotal() {
//		try {
//			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//			fullTextEntityManager.createIndexer().startAndWait();
//		} catch (InterruptedException e) {
//			System.out.println("An error occurred trying to build the serach index: " + e.toString());
//		}
//		
//	}

}
