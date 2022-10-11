package com.service.hotelmanagement.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name ="HOTAL")

@NamedQueries ({
	@NamedQuery (name ="Hotal.getAll",
			     query="SELECT ho FROM Hotal ho"),
	@NamedQuery(name ="Hotal.getById",
			  query="SELECT ho FROM Hotal ho WHERE ho.id =:id"),
	@NamedQuery(name ="Hotal.checkHotal",
			 query="SELECT ho FROM Hotal ho WHERE ho.name =:name"),
	@NamedQuery(name ="Hotal.getAllHotalDetails",
			 query="SELECT ho FROM Hotal ho WHERE ho.city =:city and ho.date =:date and ho.availablity =TRUE"),
//	@NamedQuery(name ="Hotal.checkWifi",
//			  query="SELECT ho FROM Hotal ho WHERE ho.wifi =:wifi and name = :name"),
	@NamedQuery(name ="Hotal.searchHotalByNumberOfStar",
			    query="SELECT ho FROM Hotal ho WHERE ho.numberOfStars =:numberOfStars" )
})
public class Hotal implements Serializable{
	
	private static final long serialVersionUID = 1385794955661915701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", nullable = false)
	private int id;

	@Field(store = Store.NO)
	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "TOTAL_ROOM", nullable = false)
	private int totalRoom;
	
	@Column(name = "NUMBER_OF_STAR", nullable = false)
	private int numberOfStars;
	
	@Column(name = "WIFI", nullable = false)
	private boolean wifi;
	
	@Column(name = "RESTAURANT", nullable = false)
	private boolean restaurant;
	
	@Column(name = "AIR_CONDITION", nullable = false)
	private boolean airCondition;
	
	@Column(name = "COST_OF_STAY", nullable = false)
	private int costOfStay;
	
	@Column(name = "REVIEW", nullable = false)
	private String review;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;
	
	@Column(name = "DATE", nullable = false)
	private Date date;
	
	@Column(name = "AVAILABLITY", nullable = false)
	private boolean availablity;
	
	@Column(name = "AVAILABLE_ROOM", nullable = false)
	private int availableRoom;
	
	@Column(name = "NUMBER_OF_BOOKED_ROOM", nullable = false)
	private int numberOfBookedRoom;
	
	@Column(name = "RATING", nullable = false)
	private int rating;
	
	@Column(name = "COST_OF_SINGLE_ROOM", nullable = false)
	private int costOfSingleRoom;
	
	@Transient
	private List<Review> reviewList;
	
	public Hotal() {
		super();
	}

//	public Hotal(int i, String string, String string2, int j, int k, boolean b, boolean c, boolean d, int l, int m, boolean e, int n, String string3) {
//		super();
//	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public Hotal(int id, @NotNull String name, String city, int totalRoom, int numberOfStars, boolean wifi,
		boolean restaurant, boolean airCondition, int costOfStay, String review, boolean isActive, boolean isDeleted,
		Date createdOn, Date date, boolean availablity, int availableRoom, int numberOfBookedRoom) {
	super();
	this.id = id;
	this.name = name;
	this.city = city;
	this.totalRoom = totalRoom;
	this.numberOfStars = numberOfStars;
	this.wifi = wifi;
	this.restaurant = restaurant;
	this.airCondition = airCondition;
	this.costOfStay = costOfStay;
	this.review = review;
	this.isActive = isActive;
	this.isDeleted = isDeleted;
	this.createdOn = createdOn;
	this.date = date;
	this.availablity = availablity;
	this.availableRoom = availableRoom;
	this.numberOfBookedRoom = numberOfBookedRoom;
}

	public Hotal(int i, String string, String string2, int j, int k, boolean b, boolean c, boolean d, int l, int m,
			boolean e, int n, String string3) {
	super();
	}

	public Hotal(int i, String string, String string2) {
		super();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the totalRoom
	 */
	public int getTotalRoom() {
		return totalRoom;
	}

	/**
	 * @param totalRoom the totalRoom to set
	 */
	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	/**
	 * @return the numberOfStars
	 */
	public int getNumberOfStars() {
		return numberOfStars;
	}

	/**
	 * @param numberOfStars the numberOfStars to set
	 */
	public void setNumberOfStars(int numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	/**
	 * @return the wifi
	 */
	public boolean isWifi() {
		return wifi;
	}

	/**
	 * @param wifi the wifi to set
	 */
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	/**
	 * @return the restaurant
	 */
	public boolean isRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(boolean restaurant) {
		this.restaurant = restaurant;
	}

	

	/**
	 * @return the airCondition
	 */
	public boolean isAirCondition() {
		return airCondition;
	}

	/**
	 * @param airCondition the airCondition to set
	 */
	public void setAirCondition(boolean airCondition) {
		this.airCondition = airCondition;
	}

	/**
	 * @return the costOfStay
	 */
	public int getCostOfStay() {
		return costOfStay;
	}

	/**
	 * @param costOfStay the costOfStay to set
	 */
	public void setCostOfStay(int costOfStay) {
		this.costOfStay = costOfStay;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * @return the numberOfBookedRoom
	 */
	public int getNumberOfBookedRoom() {
		return numberOfBookedRoom;
	}

	/**
	 * @param numberOfBookedRoom the numberOfBookedRoom to set
	 */
	public void setNumberOfBookedRoom(int numberOfBookedRoom) {
		this.numberOfBookedRoom = numberOfBookedRoom;
	}

	/**
	 * @return the availablity
	 */
	public boolean isAvailablity() {
		return availablity;
	}

	/**
	 * @param availablity the availablity to set
	 */
	public void setAvailablity(boolean availablity) {
		this.availablity = availablity;
	}

	/**
	 * @return the availableRoom
	 */
	public int getAvailableRoom() {
		return availableRoom;
	}

	/**
	 * @param availableRoom the availableRoom to set
	 */
	public void setAvailableRoom(int availableRoom) {
		this.availableRoom = availableRoom;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the costOfSingleRoom
	 */
	public int getCostOfSingleRoom() {
		return costOfSingleRoom;
	}

	/**
	 * @param costOfSingleRoom the costOfSingleRoom to set
	 */
	public void setCostOfSingleRoom(int costOfSingleRoom) {
		this.costOfSingleRoom = costOfSingleRoom;
	}

	/**
	 * @return the reviewList
	 */
	public List<Review> getReviewList() {
		return reviewList;
	}

	/**
	 * @param reviewList the reviewList to set
	 */
	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}
	
	
	

}
