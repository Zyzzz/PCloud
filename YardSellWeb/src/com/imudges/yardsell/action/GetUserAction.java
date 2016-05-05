package com.imudges.yardsell.action;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserAction extends ActionSupport {
	int userId;
	User user;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		UserService userservice = new UserService();
		user = userservice.getUserById(userId);
		return SUCCESS;
	}

}
