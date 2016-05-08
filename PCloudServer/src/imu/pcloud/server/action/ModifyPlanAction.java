package imu.pcloud.server.action;

import imu.pcloud.server.service.PersonalPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class ModifyPlanAction extends ActionSupport  {
	
	Integer id;
	String content;
	String name;
	String  result;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PersonalPlanService personalPlanService = new PersonalPlanService();
		int statc = personalPlanService.modify(id, content, name);
		if (statc==0){
			result = "修改成功";
		}
		else{
			result = "修改失败";
		}
		return SUCCESS;
	}
	
}
