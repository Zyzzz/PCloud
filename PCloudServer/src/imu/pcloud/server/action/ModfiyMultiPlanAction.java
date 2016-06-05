package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.MultiPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class ModfiyMultiPlanAction extends ActionSupport{

	Integer multiPlanId;
	String name;
	String content;
	Integer maxmumber;
	BaseModel result = new BaseModel();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	public Integer getMultiPlanId() {
		return multiPlanId;
	}

	public void setMultiPlanId(Integer multiPlanId) {
		this.multiPlanId = multiPlanId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMaxmumber() {
		return maxmumber;
	}

	public void setMaxmumber(Integer maxmumber) {
		this.maxmumber = maxmumber;
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
		int status = multiPlanService.modfiyMultiPlan(multiPlanId, name, content, maxmumber);
		result.setStatus(status);
		return SUCCESS;
	}
}
