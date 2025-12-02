package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comments;

@Mapper
public interface CommentMapper {
	
	/*ーListCommentーーーーーーーーーーーーーーーーー*/
	
	/*コメント情報を追加*/
	public void insertComment(@Param("comments") Comments comments);

	/*コメント情報を取得*/
	public List<Comments> selectComment();
	
	/*ーDeleteCommentーーーーーーーーーーーーーーーーー*/
	
	public Comments select(@Param("id")Integer id);

	/*コメントの削除*/
	public void deleteComment(@Param("id")Integer id);
	
}
