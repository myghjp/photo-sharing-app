package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comment;

public interface CommentService {
	
	public void add(Comment comment);
	
	public List<Comment> findAllById(int groupId);
	
	public void delete(int commentId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Comment findById(int commentId);
	
	public boolean isComment(int commentId,int userId);
	
}