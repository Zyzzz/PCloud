package com.imudges.yardsell.action;

import java.io.InputStream;





import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	private User user;
	private UserModel loginResult;
	Information information = new Information();
	public UserModel getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(UserModel loginResult) {
		this.loginResult = loginResult;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService usersService = new UserService();
		usersService.login(username, password); 
		int r = usersService.login(username, password);
		user = usersService.getUser();
		if (user == null) {
			loginResult= new UserModel(new User());
			loginResult.setStatus(r);
			loginResult.setResult(information.getErrorInfo(r));
		} else {
			loginResult = new UserModel(usersService.getUser());
			loginResult.setStatus(r);
			loginResult.setResult(information.getErrorInfo(r));
			loginResult.setVersion(new Information().getConfigInfo("appVersion"));
		}
		return SUCCESS;
	}
}
