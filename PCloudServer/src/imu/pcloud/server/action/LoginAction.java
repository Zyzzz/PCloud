package imu.pcloud.server.action;

import imu.pcloud.server.been.User;
import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;
import imu.pcloud.server.utils.Information;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String email;
	private String password;
	private UserModel result = new UserModel();
	private User user;
	
		public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserModel getResult() {
		return result;
	}

	public void setResult(UserModel result) {
		this.result = result;
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
		
		System.out.println("---------------------------------------------------------");
		System.out.println(email + "  " + password);
		UserService userService = new UserService();
		int status = userService.login(email, password);
		if(status != 0) {
			result.setResult(Information.getInstance().getErrorInfo(status));
			result.setStatus(status);
		}
		else {
			result.setByUser(userService.getUser());
			result.setResult(Information.getInstance().getErrorInfo(status));
			result.setStatus(status);
		}
		return SUCCESS;
	}
	
}
