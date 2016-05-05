package com.imudges.yardsell.action;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserInfoAction extends ActionSupport{
	private String email;
	private String hashCode;
	private UserModel userModel;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user=new UserService().getUserInfo(email, hashCode);
		if (user==null) {
			userModel=new UserModel();
			userModel.setStatus(500);
			userModel.setResult("can't get information from service!");
		}else {
			userModel=new UserModel(user);
			userModel.setStatus(0);
			userModel.setResult("get informaton success");
		}
		return SUCCESS;
	}
	
}
