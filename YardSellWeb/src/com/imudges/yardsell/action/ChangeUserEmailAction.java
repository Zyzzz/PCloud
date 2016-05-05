
package com.imudges.yardsell.action;

import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;


import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeUserEmailAction extends ActionSupport {
	int userId;
	String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userservice = new UserService();
		int r = userservice.changeUserEmail(userId, email);
		result.setStatus(r);
		result.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
	
}

