package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comments;

public interface CommentService {
	
	/*ーListCommentーーーーーーーーーーーーーーーーー*/
	
	/*commentsテーブルの情報とアカウント名を取得*/
	public List<Comments> getCommentList();
	
	/*コメント情報を追加*/
	public void addComment(Comments comments);
	
	/*ーDeleteCommentーーーーーーーーーーーーーーーーー*/
	
	/*このidとコメントを取得*/
	public Comments getComment(int id);
	
	/*コメントの削除*/
	public void deleteComment(int id);
}
