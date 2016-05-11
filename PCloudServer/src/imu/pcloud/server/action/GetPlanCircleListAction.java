package imu.pcloud.server.action;

import imu.pcloud.server.model.PlanCircleList;
import imu.pcloud.server.service.PlanCircleService;

import com.opensymphony.xwork2.ActionSupport;

public class GetPlanCircleListAction extends ActionSupport{

	PlanCircleList result = new PlanCircleList();
	PlanCircleService planCircleService = new PlanCircleService();
	
	public PlanCircleList getResult() {
		return result;
	}

	public void setResult(PlanCircleList result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub.
		int statac = planCircleService.getPlanCircleList();
		if(statac!=0){
			result.setStatus(400);
		}
		else {
			result.setPlanCircles(planCircleService.getPlanCircles());
			result.setStatus(statac);
		}
		return SUCCESS;
	}
	
	
	
	
}
