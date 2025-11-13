package portfolio.PhotoSharingApp.service.comment;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Comments;

public interface CommentService {

	/*グループID,アカウントID,テキスト入力情報 を追加*/
	public void insertCommentData(Comments comments);
	
	/*id番号,アカウント名,日時,コメント を取得*/
	public List<Comments> commentDataList();
	
	/*コメントの削除*/
	public void deleteComment(int id);
}
