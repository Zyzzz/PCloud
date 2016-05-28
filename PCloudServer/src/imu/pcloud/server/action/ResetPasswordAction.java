package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport {

	private String cookies;
	private String oldPassword;
	private String newPassword;
	private String rePassword;
	private BaseModel result;
	
	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
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

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public BaseModel getResult() {
		return result;
	}

	public void setResult(BaseModel result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		int status = userService.resetPassword(cookies, oldPassword, newPassword, rePassword);
		result.setStatus(status);
		return super.execute();
	}

	
}
