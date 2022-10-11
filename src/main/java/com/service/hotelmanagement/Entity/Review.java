package com.service.hotelmanagement.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name ="review")
@NamedQueries({
	@NamedQuery(name ="Review.getById",
			 query="SELECT re FROM Review re WHERE re.id =:id"),
	@NamedQuery(name ="Review. getByHotalId",
			  query="SELECT re FROM Review re WHERE re.hotalId =:hotalId" )
})
public class Review  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	@Column(name = "COMMENTS", nullable = false)
	private String comments;
	
	@Column(name = "RATINGS", nullable = false)
	private String ratings;
	
	@Column(name = "HOTAL_ID", nullable = false)
	private int hotalId;
	
	
	public Review() {
		super();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the ratings
	 */
	public String getRatings() {
		return ratings;
	}


	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}


	/**
	 * @return the hotalId
	 */
	public int getHotalId() {
		return hotalId;
	}


	/**
	 * @param hotalId the hotalId to set
	 */
	public void setHotalId(int hotalId) {
		this.hotalId = hotalId;
	}
	
	

}
