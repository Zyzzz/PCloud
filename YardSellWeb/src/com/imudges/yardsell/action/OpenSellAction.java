
package com.imudges.yardsell.action;

import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;



import com.imudges.yardsell.model.StatusResult;
import com.imudges.yardsell.service.UserService;
import com.imudges.yardsell.utils.Information;
import com.opensymphony.xwork2.ActionSupport;

public class OpenSellAction extends ActionSupport {
	int userId;
	StatusResult result = new StatusResult();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public StatusResult getResult() {
		return result;
	}
	public void setResult(StatusResult result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userservice = new UserService();
		int r = userservice.openSell(userId);
		result.setStatus(r);
		result.setResult(new Information().getErrorInfo(r));
		return SUCCESS;
	}
	
	
}
