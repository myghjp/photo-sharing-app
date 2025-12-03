package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comments;

@Mapper
public interface CommentMapper {
	
	/*ーListCommentーーーーーーーーーーーーーーーーー*/

	/*commentsテーブルの情報とアカウント名を取得*/
	public List<Comments> selectCommentList();
	
	/*コメント情報を追加*/
	public void insertComment(@Param("comments") Comments comments);
	
	/*ーDeleteCommentーーーーーーーーーーーーーーーーー*/
	
	/*このidとコメントを取得*/
	public Comments select(@Param("id")Integer id);

	/*コメントの削除*/
	public void deleteComment(@Param("id")Integer id);
	
}
