package imu.pcloud.server.action;

import imu.pcloud.server.model.SharingList;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class GetSharingListAction extends ActionSupport {

	private Integer planCircleId;
	private SharingRecordService sharingRecordService = new SharingRecordService();
	private SharingList result = new SharingList();
	
	
	public Integer getPlanCircleId() {
		return planCircleId;
	}


	public void setPlanCircleId(Integer planCircleId) {
		this.planCircleId = planCircleId;
	}



	public SharingList getResult() {
		return result;
	}


	public void setResult(SharingList result) {
		this.result = result;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		int statac = sharingRecordService.getSharingList(planCircleId);
		if(statac == 0){
			result.setSharingRecords(sharingRecordService.getSharingRecords());
			result.setStatus(statac);
		}else {
			result.setStatus(304);
		}
		return SUCCESS;
	}
}
