package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comments;

public interface CommentService {
	
	/*ーーーListCommentーーー*/
	
	/*このグループのcommentsテーブルの情報とアカウント名を取得*/
	public List<Comments> getCommentList(int id);
	
	public void addComment(Comments comments);
	
	/*ーーーDeleteCommentーーー*/
	
	/*このidとコメントを取得*/
	public Comments getComment(int id);
	
	public void deleteComment(int id);
}
