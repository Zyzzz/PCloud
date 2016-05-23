package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.model.UserSharingList;
import imu.pcloud.server.service.SharingRecordService;

import com.opensymphony.xwork2.ActionSupport;

public class GetUserSharingListAction extends ActionSupport {
	
	SharingRecordService sharingRecordService = new SharingRecordService();
	String cookies;
	UserSharingList result = new UserSharingList();
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public UserSharingList getResult() {
		return result;
	}
	
	public void setResult(UserSharingList result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(cookies);
		int statc = sharingRecordService.getUserSharingList(cookies); 
		if(statc==0){
			result.setStatus(303);
			result.setSharingRecords(sharingRecordService.getSharingRecords());
		}
		else {
			result.setStatus(300);
		}
		return SUCCESS;
	}
	
	
	
}
