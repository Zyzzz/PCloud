package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.MultiPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class ModfiyMultiPlanAction extends ActionSupport{

	Integer multiPlanId;
	String name;
	String content;
	Integer maxmumber;
	BaseModel result = new BaseModel();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int status = multiPlanService.modfiyMultiPlan(multiPlanId, name, content, maxmumber);
		result.setStatus(status);
		return SUCCESS;
	}
	
	
}
