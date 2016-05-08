package imu.pcloud.server.action;

import imu.pcloud.server.service.PersonalPlanService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class DeletePlanAction extends ActionSupport{

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
		UserService userService = new UserService();
		PersonalPlanService personalPlanService = new PersonalPlanService();
		int statc = userService.reLogin(cookies);
		if(statc == 0){
			int id = personalPlanService.getPlanID(content, name, userService.getUser().getId());
			statc = personalPlanService.deletePlan(id);
			if (statc ==0) {
				result = "É¾³ý³É¹¦";
			}
			else {
				result = "É¾³ýÊ§°Ü";
			}
		}
		else{
			result = "ÇëÖØÐÂµÇÂ¼";
		}
		return SUCCESS;
	}

	
}
