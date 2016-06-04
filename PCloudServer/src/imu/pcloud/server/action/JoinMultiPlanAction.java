package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.MultiPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class JoinMultiPlanAction extends ActionSupport {

	String cookies;
	Integer multiPlanId;
	BaseModel result = new BaseModel();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public Integer getMultiPlanId() {
		return multiPlanId;
	}
	public void setMultiPlanId(Integer multiPlanId) {
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
		
		UserService userService = new UserService();
		int statc = userService.reLogin(cookies);
		if(statc==0){
			statc = multiPlanService.joinMultiPlan(userService.getUser().getId(), multiPlanId);
			result.setStatus(statc);
		}
		return SUCCESS;
	}
	
	
	
}
