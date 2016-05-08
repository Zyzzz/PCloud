package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class DeletePlanAction extends ActionSupport{

	BaseModel result = new BaseModel();
	Integer id;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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
	
		PersonalPlanService personalPlanService = new PersonalPlanService();
		int statc = personalPlanService.deletePlan(id);
		if (statc ==0) {
			result.setStatus(203);
		}
		else {
			result.setStatus(204);
		}
	
		return SUCCESS;
	}

}
