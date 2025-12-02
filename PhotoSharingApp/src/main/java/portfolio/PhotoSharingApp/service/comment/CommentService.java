package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comments;

public interface CommentService {
	
	/*ーListCommentーーーーーーーーーーーーーーーーー*/
	
	/*コメント情報を追加*/
	public void addComment(Comments comments);
	
	/*コメント情報を取得*/
	public List<Comments> commentList();
	
	/*ーDeleteCommentーーーーーーーーーーーーーーーーー*/
	
	/*コメントを取得*/
	public Comments getComment(int id);
	
	/*コメントの削除*/
	public void deleteComment(int id);
}
