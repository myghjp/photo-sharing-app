package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comments;

public interface CommentService {
	
	/*ーーーListCommentーーー*/
	
	public List<Comments> getCommentList(int id);
	
	public void addComment(Comments comments);
	
	/*ーーーDeleteCommentーーー*/
	
	public Comments getComment(int id);
	
	public void deleteComment(int id);
	
	/*比較を作成*/
	public int isCurrentUser(int id);
}