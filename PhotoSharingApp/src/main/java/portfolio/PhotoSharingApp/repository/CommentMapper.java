package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comments;

@Mapper
public interface CommentMapper {
	
	/*グループID,アカウントID,テキスト入力情報 を追加*/
	public void insertCommentData(@Param("comments") Comments comments);

	/*id番号,アカウント名,日時,コメント を取得*/
	public List<Comments> selectCommentData();

	/*コメントの削除*/
	public void deleteComment(@Param("id")int id);
	
}
