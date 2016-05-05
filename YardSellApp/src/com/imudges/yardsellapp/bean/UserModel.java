package com.imudges.yardsellapp.bean;

import android.R.integer;


public class UserModel extends User {

	private int status;
	private String result;
	private String version;

	public UserModel() {
		
	}
	
	public UserModel(User user) {
		setUser(user);
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
		setSellDate(user.getSellDate());
		setUserId(user.getUserId());
		setUserName(user.getUserName());
		setVersion(user.getVersion());
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
}

