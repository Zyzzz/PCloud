package imu.pcloud.server.action;

import imu.pcloud.server.model.MultiPlanList;
import imu.pcloud.server.service.MultiPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class GetMultiPlanMembers extends ActionSupport {
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
		return SUCCESS;
	}
	
	
	
	
}
