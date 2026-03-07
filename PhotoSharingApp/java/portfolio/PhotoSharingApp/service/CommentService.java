package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comment;

public interface CommentService {
	
	public void add(Comment comment);
	
	public List<Comment> getCommentList(int groupId);
	
	public void delete(int commentId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Comment findById(int commentId);
	
	public boolean hasPostComment(int commentId,int userId);
	
}