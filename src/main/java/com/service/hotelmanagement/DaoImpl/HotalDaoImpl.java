package com.service.hotelmanagement.DaoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.service.hotelmanagement.Entity.Hotal;
import com.service.hotelmanagement.IDao.IHotalDao;

@Repository
@Transactional
public class HotalDaoImpl implements IHotalDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	public HotalDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Hotal> getAll() {
	List<Hotal> hotalList = entityManager.createNamedQuery("Hotal.getAll", Hotal.class).getResultList();
			
	return null != hotalList ? hotalList : null;
		
	}

	@Override
	public Hotal getById(int id) {
	Hotal hotal = entityManager.createNamedQuery("Hotal.getById", Hotal.class).setParameter("id", id).getResultList().stream().findFirst()
			.orElse(null);
	return null != hotal ? hotal : null;
	}

	@Override
	public boolean exitsHotal(String name) {
	Hotal hotal = entityManager.createNamedQuery("Hotal.checkHotal", Hotal.class).setParameter("name", name).getResultList().stream().findFirst().orElse(null);;
	return null != hotal ?Boolean.TRUE:Boolean.FALSE;
		
	}

	@Override
	public void addHotal(Hotal hotal) {
		entityManager.persist(hotal);
		
	}

	@Override
	public void updateHotal(Hotal hotal) {
		entityManager.merge(hotal);
		
	}

	@Override
	public boolean DeleteHotal(int id) {
		Query query = entityManager.createQuery("delete Hotal where id = " + id);
		query.executeUpdate();
		entityManager.flush();
		return true;
	}

	@Override
	public void indexHotal() {
		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			System.out.println("An error occurred trying to build the serach index: " + e.toString());
		}
		
	}

	@Override
	public List<Hotal> searchBrand(String keyword) {
		// get the full text entity manager
				FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
						.getFullTextEntityManager(entityManager);

				// create the query using Hibernate Search query DSL
				QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Hotal.class)
						.get();

				// a very basic query by keywords
				org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("name", "name").matching(keyword)
						.createQuery();

				// wrap Lucene query in an Hibernate Query object
				org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Hotal.class);

				// execute search and return results (sorted by relevance as default)
				@SuppressWarnings("unchecked")
				List<Hotal> results = (List<Hotal>) jpaQuery.getResultList();
		return results;
	}

	@Override
	public Hotal getHotalByName(String name) {
		Hotal hotal =entityManager.createNamedQuery("Hotal.checkHotal", Hotal.class).setParameter("name", name).getSingleResult();
		return hotal;
	}

	@Override
	public List<Hotal> getHotalByDateAndCity(String city, Date date) {
		List<Hotal> hotalList = entityManager.createNamedQuery("Hotal.getAllHotalDetails", Hotal.class).setParameter("city", city).setParameter("date", date).getResultList();
		return hotalList;
	}

	@Override
	public boolean checkWifi(boolean wifi, String name) {
		Hotal hotal = entityManager.createNamedQuery("Hotal.checkWifi", Hotal.class).setParameter("wifi", wifi).setParameter("name", name).getSingleResult();
		return null != hotal ?Boolean.TRUE :Boolean.FALSE;
	}

	@Override
	public List<Hotal> searchHotalByNumberOfStar(int numberOfStars) {
		List<Hotal> hotalList = entityManager.createNamedQuery("Hotal.searchHotalByNumberOfStar", Hotal.class).setParameter("numberOfStars", numberOfStars).getResultList();
		return hotalList;
	}

}
