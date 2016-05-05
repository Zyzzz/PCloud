package com.imudges.yardsellapp.bean;

import java.util.Date;

import android.R.integer;
import android.R.string;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String email;
	private String userName;
	private String password;
	private String address;
	private String phoneNumber;
	private Integer isOpen;
	private Double latitude;
	private Double longtitude;
	private String commodityInfo;
	private Date sellDate;
	private String hashCode;
	private Date endDate;
	private Integer isVerify;
	private String oldpwd;
	private String newpwd;
	private String emailDate;
	private Integer image1;
	private Integer image2;
	private Integer image3;
	private String version;
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public User(String email, String userName, String password, String address,
			String phoneNumber, Integer isOpen, Double latitude,
			Double longtitude, String commodityInfo, Date sellDate,
			String hashCode, Date endDate, Integer isVerify) {
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.isOpen = isOpen;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.commodityInfo = commodityInfo;
		this.sellDate = sellDate;
		this.hashCode = hashCode;
		this.endDate = endDate;
		this.isVerify = isVerify;
	}

	// Property accessors
	
	

	public String getOldpwd() {
		return oldpwd;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public String getCommodityInfo() {
		return this.commodityInfo;
	}

	public void setCommodityInfo(String commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public Date getSellDate() {
		return this.sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getHashCode() {
		return this.hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getIsVerify() {
		return this.isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public String getEmailDate() {
		return emailDate;
	}

	public void setEmailDate(String emailDate) {
		this.emailDate = emailDate;
	}

	public Integer getImage1() {
		return image1;
	}

	public void setImage1(Integer image1) {
		this.image1 = image1;
	}

	public Integer getImage2() {
		return image2;
	}

	public void setImage2(Integer image2) {
		this.image2 = image2;
	}

	public Integer getImage3() {
		return image3;
	}

	public void setImage3(Integer image3) {
		this.image3 = image3;
	}
	
}