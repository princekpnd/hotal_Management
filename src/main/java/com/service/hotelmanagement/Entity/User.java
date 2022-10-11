package com.service.hotelmanagement.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name ="user")
@NamedQueries({
	@NamedQuery(name ="User.getById",
			     query ="SELECT ur FROM User ur WHERE ur.id =:id"),
	@NamedQuery(name ="User.checkUser",
			    query ="SELECT ur FROM User ur WHERE ur.firstName =:firstName and ur.mobileNumber =:mobileNumber"),
	@NamedQuery(name ="User.getByMobileNumber",
			    query ="SELECT ur FROM User ur WHERE ur.mobileNumber =:mobileNumber")
})
public class User  implements Serializable{
	private static final long serialVersionUID = 1385794955661915701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "MOBILE_NUMBER", nullable = false)
	private String mobileNumber;
	
	@Column(name = "AADHAR_NUMBER", nullable = false)
	private String aadharNumber;
	
	@Column(name = "HOTAL_NAME", nullable = false)
	private String hotalName;
	
	@Column(name = "HOTAL_ID", nullable = false)
	private int hotalId;
	
	@Column(name = "IS_ACTIVE", nullable = false)
	private boolean isActive;
	
	@Column(name = "IS_DELETED", nullable = false)
	private boolean isDeleted;
	
	@Column(name = "CREATED_ON", nullable = false)
	private Date createdOn;
	
	
	
	public User() {
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}



	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}



	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
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
	 * @return the hotalName
	 */
	public String getHotalName() {
		return hotalName;
	}



	/**
	 * @param hotalName the hotalName to set
	 */
	public void setHotalName(String hotalName) {
		this.hotalName = hotalName;
	}

	
	
}
