package com.imudges.yardsell.action;

import java.util.ArrayList;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class FindSaleAction extends ActionSupport {

	String lat;
	String lng;
	String scope;
	String startDate;
	String endDate;

	ArrayList<UserModel> list;
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ArrayList<UserModel> getList() {
		return list;
	}

	public void setList(ArrayList<UserModel> list) {
		this.list = list;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("lat:" + lat + "lng:" + lng + "scope=" + scope);
		UserService userService = new UserService();
		list = (ArrayList<UserModel>) userService.findSellByScope(Double.parseDouble(lat), Double.parseDouble(lng), Integer.parseInt(scope));
		return SUCCESS;
	}

	
}
