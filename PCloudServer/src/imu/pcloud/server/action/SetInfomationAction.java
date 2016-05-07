package imu.pcloud.server.action;

import java.util.Date;

import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class SetInfomationAction extends ActionSupport {

	private String cookies;
	private UserModel result = new UserModel();
	private String sex;
	private String birthday;
	private String education;
	private String working;
	private String signature;
	
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorking() {
		return working;
	}

	public void setWorking(String working) {
		this.working = working;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(cookies);
		UserService userService = new UserService();
		int status = userService.setInformation(cookies, sex, birthday, education, working, signature);
		result.setStatus(status);
		if(status == 0)
			result.setByUser(userService.getUser());
		return SUCCESS;
	}
}
