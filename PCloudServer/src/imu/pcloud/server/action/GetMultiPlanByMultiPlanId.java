package imu.pcloud.server.action;

import imu.pcloud.server.model.MultiPlanList;
import imu.pcloud.server.service.MultiPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class GetMultiPlanByMultiPlanId extends ActionSupport {

	Integer multiPlanId;
	MultiPlanList result = new MultiPlanList();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	public Integer getMultiPlanId() {
		return multiPlanId;
	}
	public void setMultiPlanId(Integer multiPlanId) {
		this.multiPlanId = multiPlanId;
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
		int stauts = multiPlanService.getMultiPlanByMultiPlanId(multiPlanId);
		if(stauts == 0){
			result.setMultiPlan(multiPlanService.getMultiPlan());
			result.setMultiPlanMembers(multiPlanService.getMultiPlanMembers());
			result.setStatus(stauts);
		}else {
			result.setStatus(602);
		}
		return SUCCESS;
	}
	
	
	
}
