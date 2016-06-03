package imu.pcloud.server.action;

import imu.pcloud.server.been.Comment;
import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.model.CommentList;
import imu.pcloud.server.service.CommentService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class AddCommentAction extends ActionSupport {

	CommentService commentService = new CommentService();
	String cookies;
	Integer personalPlanId;
	String content;
	//CommentList commentList = new CommentList();
	BaseModel result = new BaseModel();
	
	
	

	public String getCookies() {
		return cookies;
	}


	public void setCookies(String cookies) {
		this.cookies = cookies;
	}


	public Integer getPersonalPlanId() {
		return personalPlanId;
	}


	public void setPersonalPlanId(Integer personalPlanId) {
		this.personalPlanId = personalPlanId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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
		System.out.println(cookies);
		System.out.println(content);
		//System.out.println(name);
		UserService userService = new UserService();
		int statc = userService.reLogin(cookies);
		if(statc==0){
			 statc = commentService.addComment(userService.getUser().getId(), personalPlanId, content);
			 if(statc == 0){
				 result.setStatus(statc);
			 }
			 else{
				 result.setStatus(503);
			 }
		}
		else{
			result.setStatus(104);
		}
		return SUCCESS;
	}

}
