
package com.imudges.yardsell.action;

import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;


import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport {
	String oldPassword;
	String newPassword;
	String repeatPassword;
	String email;
	String hashCode;
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
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(email);
		System.out.println(hashCode);
		UserService userservice = new UserService();
		int userId=userservice.getUserId(email, hashCode);
		int r = userservice.resetPassword(userId, oldPassword, newPassword, repeatPassword);
		userModel=new UserModel(new User());
		userModel.setStatus(r);
		userModel.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
}

