package imu.pcloud.server.action;

import java.util.List;

import imu.pcloud.server.been.PlanCircle;
import imu.pcloud.server.service.PlanCircleService;

import com.opensymphony.xwork2.ActionSupport;

public class GetAllPlanCircleAction extends ActionSupport {

	private List<PlanCircle> result;
	
	public List<PlanCircle> getResult() {
		return result;
	}

	public void setResult(List<PlanCircle> result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PlanCircleService planCircleService = new PlanCircleService();
		planCircleService.findAllPlanCircle();
		result = planCircleService.getPlanCircleList();
		return super.execute();
	}
}
