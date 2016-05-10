package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteSharingAction extends ActionSupport {
	SharingRecordService sharingRecordService = new SharingRecordService(); 
	Integer personalPlanId;
	Integer planCircleId;
	BaseModel result = new BaseModel();
	
	
	public Integer getPlanCircleId() {
		return planCircleId;
	}

	public void setPlanCircleId(Integer planCircleId) {
		this.planCircleId = planCircleId;
	}

	public Integer getPersonalPlanId() {
		return personalPlanId;
	}

	public void setPersonalPlanId(Integer personalPlanId) {
		this.personalPlanId = personalPlanId;
	}


	public BaseModel getResult() {
		return result;
	}

	public void setResult(BaseModel result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub		
		int statc = sharingRecordService.deleteSharing(personalPlanId,planCircleId);
		if(statc == 0){
			result.setStatus(302);
		}
		else{
			result.setStatus(statc);
		}
		return super.execute();
	}

}
