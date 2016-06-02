package imu.pcloud.server.action;

import imu.pcloud.server.been.Comment;
import imu.pcloud.server.model.CommentList;
import imu.pcloud.server.service.CommentService;

import com.opensymphony.xwork2.ActionSupport;

public class GetCommentListAction extends ActionSupport {

	Integer personalPlanId;
	
	CommentService commentService = new CommentService();
	CommentList result = new CommentList();
	
	public Integer getPersonalPlanId() {
		return personalPlanId;
	}


	public void setPersonalPlanId(Integer personalPlanId) {
		this.personalPlanId = personalPlanId;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int stata = commentService.getCommentList(personalPlanId);
		if(stata==0){
			result.setStatus(stata);
			result.setComments(commentService.getComments());
		}
		else{
			result.setStatus(stata);
		}
		return SUCCESS;
	}

	
	
}
