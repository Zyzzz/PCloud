package imu.pcloud.server.action;

import imu.pcloud.server.model.MultiPlanList;
import imu.pcloud.server.service.MultiPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class RetMultiPlanListByUserIdAction extends ActionSupport {
	String cookies;
	MultiPlanList result = new MultiPlanList();
	MultiPlanService multiPlanService = new MultiPlanService();
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public MultiPlanList getResult() {
		return result;
	}
	public void setResult(MultiPlanList result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		int statc = userService.reLogin(cookies);
		if(statc==0){
			 statc = multiPlanService.getMultiPlanListByUserId(userService.getUser().getId());
			 if(statc == 0){
				 result.setMultiPlanMembers(multiPlanService.getMultiPlanMembers());
				 result.setMultiPlans(multiPlanService.getMultiPlans());
				 result.setStatus(0);
			 }else {
				result.setStatus(statc);
			}
			 
		}else {
			result.setStatus(104);
		}
		return SUCCESS;
	}
	
	
}
