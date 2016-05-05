package com.imudges.yardsell.webAction;

import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class ActivateEmailAction extends ActionSupport {

	private String email;
	private String key;
	private String result;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		int resultCode = userService.checkActivateEmail(email, key);
		result = new Information().getErrorInfo(resultCode);
		if(resultCode == 0)
			return "success";
		else
			return "error";
	}
}
