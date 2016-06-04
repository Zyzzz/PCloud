package imu.pcloud.server.action;

import imu.pcloud.server.model.MultiPlanList;
import imu.pcloud.server.service.MultiPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class GetMultiPlanListByBlurryNameAction extends ActionSupport {
	String blurryName;
	MultiPlanList result = new MultiPlanList();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	public String getBlurryName() {
		return blurryName;
	}
	public void setBlurryName(String blurryName) {
		this.blurryName = blurryName;
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
		int status = multiPlanService.getMultiPlanListByBlurryName(blurryName);
		if(status == 0){
			result.setMultiPlans(multiPlanService.getMultiPlans());
			result.setStatus(0);
		}
		else
			result.setStatus(status);
		return SUCCESS;
	}
	
	
	
}
