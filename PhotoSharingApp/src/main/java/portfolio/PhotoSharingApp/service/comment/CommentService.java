package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comment;

public interface CommentService {
	
	public void addComment(Comment comment);
	
	public void deleteComment(int id);
	
	public List<Comment> getCommentList(int id);
	
	public Comment getComment(int id);
	
	public boolean isCurrentAccount(int id,int loginId);
}