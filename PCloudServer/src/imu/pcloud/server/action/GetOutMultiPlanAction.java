package imu.pcloud.server.action;

import imu.pcloud.server.been.User;
import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.MultiPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class GetOutMultiPlanAction extends ActionSupport {
	String cookies;
	int multiPlanId;
	BaseModel result = new BaseModel();
	MultiPlanService multiPlanService = new MultiPlanService();
	UserService userService = new UserService();
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public int getMultiPlanId() {
		return multiPlanId;
	}
	public void setMultiPlanId(int multiPlanId) {
		this.multiPlanId = multiPlanId;
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
		int code = userService.reLogin(cookies);
		if(code != 0) {
			result.setStatus(code);
		}
		else {
			User u = userService.getUser();
			code = multiPlanService.getOutMultiplan(u.getId(), multiPlanId);
			result.setStatus(code);
		}
		return super.execute();
	}
	
}
