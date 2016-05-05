package com.imudges.yardsell.action;

import java.sql.Timestamp;
import java.util.Date;

import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class SetSellInfoAction extends ActionSupport{
	int userId;
	String address;
	String phoneNumber;
	double latitude;
	double longtitude;
	String commodityInfo;
	long sellDate;
	long endDate;
	StatusResult result = new StatusResult();
	
	
	public StatusResult getResult() {
		return result;
	}
	public void setResult(StatusResult result) {
		this.result = result;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
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
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//address = new String(address.getBytes("ISO-8859-1"),"utf-8");
		UserService userservice = new UserService();
		System.out.println("userId:" + userId + "address:" + address + "phoneNumber:" + phoneNumber + "lat:" + latitude
				+ "lng:" + longtitude + "commodityInfo:" +commodityInfo + "sellDate:" + sellDate + "endDate:" + endDate);
		int r = userservice.setSellInfo(userId, address, phoneNumber, latitude, 
				longtitude, commodityInfo, new Timestamp(sellDate), new Timestamp(endDate));
		System.out.println("*************************" + r);
		result.setStatus(r);
		result.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
	
	
	
}
