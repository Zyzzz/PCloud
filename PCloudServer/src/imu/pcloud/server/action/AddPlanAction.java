package imu.pcloud.server.action;

import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class AddPlanAction extends ActionSupport {

	String content;
	String name;
	String cookies;
	String result;
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
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
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
		UserService userService = new UserService();
		int statc = userService.reLogin(cookies);
		if (statc==0){
			statc =personalPlanService.addPlan(content, name, userService.getUser().getId());
			if(statc==0){
				result = "创建成功";
			}
			else {
				result = "创建失败";
			}
		}
		else {
			result = "请重新登录";
		}
		return SUCCESS;
	}
}
