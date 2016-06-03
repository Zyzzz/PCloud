package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.model.PlanSharingListModel;
import imu.pcloud.server.service.PlanSharingService;
import imu.pcloud.server.service.SharingRecordService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class GetUserSharingListAction extends ActionSupport {
	
	SharingRecordService sharingRecordService = new SharingRecordService();
	String cookies;
	PlanSharingListModel result = new PlanSharingListModel();
	
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
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
		System.out.println(cookies);
		PlanSharingService planSharingService = new PlanSharingService();
		UserService userService = new UserService();
		int status = userService.reLogin(cookies);
		if(status != 0) {
			result.setStatus(status);
			return SUCCESS;
		}
		planSharingService.findPersonalSharing(userService.getUser().getId());
		result = planSharingService.getPlanSharingListModel();
		result.setStatus(400);
		return SUCCESS;
	}	
}
