package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.PersonalPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class ModifyPlanAction extends ActionSupport  {
	
	Integer id;
	String content;
	String name;
	BaseModel  result = new BaseModel();
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		int statc = personalPlanService.modify(id, content, name);
		if (statc==0){
			result.setStatus(205);
		}
		else{
			result.setStatus(206);
		}
		return SUCCESS;
	}
	
}
