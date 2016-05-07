package imu.pcloud.server.action;

import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	private String cookies;
	private int result;
	
	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(cookies);
		UserService userService = new UserService();
		int status = userService.logout(cookies);
		result = 0;
		return SUCCESS;
	}
}
