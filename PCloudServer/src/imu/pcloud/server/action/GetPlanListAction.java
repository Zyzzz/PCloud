package imu.pcloud.server.action;

import imu.pcloud.server.been.PersonalPlan;
import imu.pcloud.server.been.User;
import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class GetPlanListAction extends ActionSupport {
	
	List<PersonalPlan> result = new ArrayList();
	String cookies;
	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public List<PersonalPlan> getResult() {
		return result;
	}

	public void setResult(List<PersonalPlan> result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		PersonalPlanService personalPlanService = new PersonalPlanService();
		int Result = userService.reLogin(cookies);
		if(Result==0){
			User user = userService.getUser();
			System.out.println(user.getId());
			result = personalPlanService.getPlanList(user.getId());
		}
		//System.out.println("---------------------------------------------------------");
		//System.out.print(result.get(0).getContent());
		return SUCCESS;
	}

}
