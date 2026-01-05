package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comment;

public interface CommentService {
	
	/*ーーーListCommentーーー*/
	
	public List<Comment> getCommentList(int id);
	
	public void addComment(Comment comment);
	
	/*ーーーDeleteCommentーーー*/
	
	public Comment getComment(int id);
	
	public void deleteComment(int id);
	
	/*比較を作成*/
	public boolean isCurrentAccount(int id,int loginId);
}