package imu.pcloud.server.action;

import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.service.MultiPlanService;
import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class AddMultiPlanAction extends ActionSupport {
	String cookies;
	String name;
	String content;
	Integer maxmumber;
	Integer condition;
	BaseModel result = new BaseModel();
	MultiPlanService multiPlanService = new MultiPlanService();
	
	
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
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
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
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
		
		UserService userService = new UserService();
		int statc = userService.reLogin(cookies);
		if (statc==0){
			multiPlanService.addMultiPlan(userService.getUser().getId(), name, content, maxmumber, condition);
		
		}else{
			result.setStatus(104);
		}
	return SUCCESS;
	}
}
