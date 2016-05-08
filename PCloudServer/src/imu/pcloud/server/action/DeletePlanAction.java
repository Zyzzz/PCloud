package imu.pcloud.server.action;

import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class DeletePlanAction extends ActionSupport{

	String result;
	Integer id;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	
		PersonalPlanService personalPlanService = new PersonalPlanService();
		int statc = personalPlanService.deletePlan(id);
		if (statc ==0) {
			result = "É¾³ý³É¹¦";
		}
		else {
			result = "É¾³ýÊ§°Ü";
		}
	
		return SUCCESS;
	}

}
