package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class IncreaseLoadingTimeAction extends ActionSupport {

	SharingRecordService sharingRecordService = new SharingRecordService(); 
	Integer personalPlanId;
	Integer planCircleId;
	BaseModel result = new BaseModel();
	
	public Integer getPersonalPlanId() {
		return personalPlanId;
	}
	public void setPersonalPlanId(Integer personalPlanId) {
		this.personalPlanId = personalPlanId;
	}
	public Integer getPlanCircleId() {
		return planCircleId;
	}
	public void setPlanCircleId(Integer planCircleId) {
		this.planCircleId = planCircleId;
	}
	public BaseModel getResult() {
		return result;
	}
	public void setResult(BaseModel result) {
		this.result = result;
	}
	
	
	
	
}
