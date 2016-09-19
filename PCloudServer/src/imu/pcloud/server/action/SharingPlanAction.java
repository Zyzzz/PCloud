package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class SharingPlanAction extends ActionSupport {
	
	SharingRecordService sharingRecordService = new SharingRecordService();
	Integer personalPlanId;
	Integer planCircleId;
	String describe;
	String cookies;
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


	public String getCookies() {
		return cookies;
	}


	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	
	public BaseModel getResult() {
		return result;
	}


	public void setResult(BaseModel result) {
		this.result = result;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		result = new BaseModel();
		System.out.println(personalPlanId + ":" + planCircleId + "/" + cookies);
		int statc = sharingRecordService.Sharing(personalPlanId, planCircleId, describe, cookies);
		if(statc == 0){
			result.setStatus(301);
		}
		else{
			result.setStatus(statc);
		}
		return SUCCESS;
	}
	
	
	
}
