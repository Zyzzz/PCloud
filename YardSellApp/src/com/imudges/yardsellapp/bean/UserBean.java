package com.imudges.yardsellapp.bean;

import java.util.Date;

import android.R.string;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class UserBean implements java.io.Serializable {

	// Fields

	private int userId;
	private String email;
	private String userName;
	private String password;
	private String address;
	private String phoneNumber;
	private int isOpen;
	private double latitude;
	private double longtitude;
	private String commodityInfo;
	private long sellDate;
	private String hashCode;
	private long endDate;
	private int isVerify;
	private String oldpwd;
	private String newpwd;
	private String emailDate;
	private int image1;
	private int image2;
	private int image3;
	private int version;
	// Constructors

	/** default constructor */
	public UserBean() {
	}

	/** minimal constructor */
	public UserBean(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public UserBean(String email, String userName, String password, String address,
			String phoneNumber, int isOpen, double latitude,
			double longtitude, String commodityInfo, Date sellDate,
			String hashCode, Date endDate, int isVerify) {
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.isOpen = isOpen;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.commodityInfo = commodityInfo;
		this.sellDate = sellDate.getTime();
		this.hashCode = hashCode;
		this.endDate = endDate.getTime();
		this.isVerify = isVerify;
	}

	// Property accessors

	public void setUser(User u) {
		User user = getDefault(u);
		setAddress(user.getAddress());
		setCommodityInfo(user.getCommodityInfo());
		setEmail(user.getEmail());
		
		setHashCode(user.getHashCode());
		setIsOpen(user.getIsOpen());
		setLatitude(user.getLatitude());
		setLongtitude(user.getLongtitude());
		setPhoneNumber(user.getPhoneNumber());
		setSellDate(user.getSellDate().getTime());
		setUserId(user.getUserId());
		setUserName(user.getUserName());
		setEndDate(user.getEndDate().getTime());
	}
	
	public static User getDefault(User u) {
		User user = new User();
		if(u.getAddress() == null)
			user.setAddress("/");
		else 
			user.setAddress(u.getAddress());
		if(u.getCommodityInfo() == null)
			user.setCommodityInfo("/");
		else
			user.setCommodityInfo(u.getCommodityInfo());
		if(u.getEmail() == null)
			user.setEmail("/");
		else
			user.setEmail(u.getEmail());
		if(u.getHashCode() == null)
			user.setHashCode("/");
		else
			user.setHashCode(u.getHashCode());
		if(u.getIsOpen() == null)
			user.setIsOpen(0);
		else
			user.setIsOpen(u.getIsOpen());
		if(u.getLatitude() == null)
			user.setLatitude(0.0);
		else
			user.setLatitude(u.getLatitude());
		if(u.getLongtitude() == null)
			user.setLongtitude(0.0);
		else
			user.setLongtitude(u.getLongtitude());
		if(u.getPhoneNumber() == null)
			user.setPhoneNumber("/");
		else
			user.setPhoneNumber(u.getPhoneNumber());
		if(u.getSellDate() == null)
			user.setSellDate(new Date(0));
		else
			user.setSellDate(u.getSellDate());
		if(u.getEndDate() == null)
			user.setEndDate(new Date(0));
		else
			user.setEndDate(u.getEndDate());
		if(u.getUserId() == null)
			user.setUserId(-1);
		else
			user.setUserId(u.getUserId());
		if(u.getUserName() == null)
			user.setUserName("/");
		else
			user.setUserName(u.getUserName());
		return user;
	}
	
	
	public UserBean(User user){
		setUser(user);
	}
	public int getUserId() {
		return this.userId;
	}

	public String getOldpwd() {
		return oldpwd;
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

	public void setUserId(int userId) {
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

	public int getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getCommodityInfo() {
		return this.commodityInfo;
	}

	public void setCommodityInfo(String commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public long getSellDate() {
		return this.sellDate;
	}

	public void setSellDate(long sellDate) {
		this.sellDate = sellDate;
	}

	public String getHashCode() {
		return this.hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public long getEndDate() {
		return this.endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public int getIsVerify() {
		return this.isVerify;
	}

	public void setIsVerify(int isVerify) {
		this.isVerify = isVerify;
	}

	public String getEmailDate() {
		return emailDate;
	}

	public void setEmailDate(String emailDate) {
		this.emailDate = emailDate;
	}

	public int getImage1() {
		return image1;
	}

	public void setImage1(int image1) {
		this.image1 = image1;
	}

	public int getImage2() {
		return image2;
	}

	public void setImage2(int image2) {
		this.image2 = image2;
	}

	public int getImage3() {
		return image3;
	}

	public void setImage3(int image3) {
		this.image3 = image3;
	}
	
	
	
}