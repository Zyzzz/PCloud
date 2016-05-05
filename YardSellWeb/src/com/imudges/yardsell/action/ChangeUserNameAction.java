
package com.imudges.yardsell.action;

import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeUserNameAction extends ActionSupport {
	String userName;
	String email;
	String hashCode;
	String phoneNum;
	UserModel userModel;
	
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(userName);
		UserService userservice = new UserService();
		int userId=userservice.getUserId(email, hashCode);
		int r = userservice.changeUserName(userId, userName);
		r = userservice.setPhoneNum(userId, phoneNum);
		userModel=new UserModel(new User());
		userModel.setStatus(r);
		userModel.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
}

