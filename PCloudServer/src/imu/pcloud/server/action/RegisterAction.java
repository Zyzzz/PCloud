package imu.pcloud.server.action;

import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	private String email;
	private String username;
	private String password;
	private String rePassword;
	private UserModel result = new UserModel();
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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
		UserService userService = new UserService();
		int status = userService.register(username, email, password, rePassword);
		if(status == 0)
			result.setByUser(userService.getUser());
		result.setStatus(status);
		return SUCCESS;
	}
	
}
