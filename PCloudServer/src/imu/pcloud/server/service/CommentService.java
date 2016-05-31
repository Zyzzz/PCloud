package imu.pcloud.server.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import imu.pcloud.server.DAO.CommentDAO;
import imu.pcloud.server.been.Comment;
import imu.pcloud.server.been.CommentId;

public class CommentService {
	private CommentDAO commentDao = new CommentDAO();
	private Comment comment = new Comment();
	private List<Comment> comments = new ArrayList();
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int addComment(Integer userId,Integer personalPlanId, String content) {
		CommentId commentId = new CommentId();
		commentId.setPersonalPlanId(personalPlanId);
		commentId.setUserId(userId);
		comment.setId(commentId);
		comment.setContent(content);
		commentDao.save(comment);
		return 0;
	}
	
	public int getCommentList(Integer personalPlanId){
		//commentDao.findByContent(content);
		comments = commentDao.findByPersonalPlanId(personalPlanId);
		if(!comments.isEmpty())
			return 0;
		else {
			return 501;
		}
	}
	
}
