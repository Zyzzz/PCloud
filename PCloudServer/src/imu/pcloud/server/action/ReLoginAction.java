package imu.pcloud.server.action;

import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class ReLoginAction extends ActionSupport {

	private String cookies;
	private UserModel result = new UserModel();
	
	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public UserModel getResult() {
		return result;
	}

	public void setResult(UserModel result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-" +cookies + "-");
		UserService userService = new UserService();
		int status = userService.reLogin(cookies);
		result.setStatus(status);
		if(status == 0)
			result.setByUser(userService.getUser());
		return SUCCESS;
	}
}
