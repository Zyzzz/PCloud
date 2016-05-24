package imu.pcloud.server.action;

import imu.pcloud.server.model.PlanSharingListModel;
import imu.pcloud.server.model.SharingList;
import imu.pcloud.server.service.PlanSharingService;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class GetSharingListAction extends ActionSupport {

	private Integer planCircleId;
	SharingRecordService sharingRecordService = new SharingRecordService();
	private PlanSharingListModel result = new PlanSharingListModel();
	
	public Integer getPlanCircleId() {
		return planCircleId;
	}


	public void setPlanCircleId(Integer planCircleId) {
		this.planCircleId = planCircleId;
	}

	public PlanSharingListModel getResult() {
		return result;
	}


	public void setResult(PlanSharingListModel result) {
		this.result = result;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PlanSharingService planSharingService = new PlanSharingService();
		planSharingService.findPersonalSharingByCircleID(planCircleId);
		result = planSharingService.getPlanSharingListModel();
		return SUCCESS;
	}
}
