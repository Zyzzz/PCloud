package com.imudges.yardsell.model;

import java.util.Date;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.service.ImageService;

public class UserModel{



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
	private long sellDate;
	private String hashCode;
	private long endDate;
	private Integer isVerify;
	private String emailDate;
	private Integer image1;
	private Integer image2;
	private Integer image3;
	
	private String imageUrl_1;
	private String imageUrl_2;
	private String imageUrl_3;
	
	
	private int status;
	private String result;
	
	private String version;
	public UserModel() {
		
	}
	
	public UserModel(User user) {
		setUser(user);
	}
	
	public String getImageUrl_1() {
		return imageUrl_1;
	}

	public void setImageUrl_1(String imageUrl_1) {
		this.imageUrl_1 = imageUrl_1;
	}

	public String getImageUrl_2() {
		return imageUrl_2;
	}

	public void setImageUrl_2(String imageUrl_2) {
		this.imageUrl_2 = imageUrl_2;
	}

	public String getImageUrl_3() {
		return imageUrl_3;
	}

	public void setImageUrl_3(String imageUrl_3) {
		this.imageUrl_3 = imageUrl_3;
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

	public String getEmailDate() {
		return emailDate;
	}

	public void setEmailDate(String emailDate) {
		this.emailDate = emailDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public String getCommodityInfo() {
		return commodityInfo;
	}

	public void setCommodityInfo(String commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public long getSellDate() {
		return sellDate;
	}

	public void setSellDate(long sellDate) {
		this.sellDate = sellDate;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public void setUser(User user) {
		setAddress(user.getAddress());
		setCommodityInfo(user.getCommodityInfo());
		setEmail(user.getEmail());
		setHashCode(user.getHashCode());
		setIsOpen(user.getIsOpen());
		setLatitude(user.getLatitude());
		setLongtitude(user.getLongtitude());
		setPhoneNumber(user.getPhoneNumber());
		if(user.getSellDate() != null)
			setSellDate(user.getSellDate().getTime());
		setUserId(user.getUserId());
		setUserName(user.getUserName());
		if(user.getEndDate() != null)
			setEndDate(user.getEndDate().getTime());
		setIsVerify(user.getIsVerify());
		setEmailDate(user.getEmailDate());
		setPassword(user.getPassword());
		/*setImage1(user.getImage1());
		setImage2(user.getImage2());
		setImage3(user.getImage3());
		String[] urlArr = new String[3];
		urlArr = new ImageService().getUserImageUrl(userId);
		setImageUrl_1(urlArr[0]);
		setImageUrl_2(urlArr[1]);
		setImageUrl_3(urlArr[2]);*/
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	
}
