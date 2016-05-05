
package com.imudges.yardsell.action;

import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	String email;
	String password;
	String repeatPassword;
	String userName;
	UserModel userModel;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		UserService userservice = new UserService();
		int r = userservice.register(email, password,  repeatPassword, userName);
		userModel=new UserModel(userservice.getUser());
		userModel.setStatus(r);
		userModel.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
}

