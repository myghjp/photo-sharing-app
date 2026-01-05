package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comment;

@Mapper
public interface CommentMapper {
	
	/*ーーーListCommentーーー*/

	public List<Comment> selectCommentItems(@Param("id")Integer id);
	
	public void insert(@Param("comment") Comment comment);
	
	/*ーーーDeleteCommentーーー*/
	
	public Comment selectById(@Param("id")Integer id);

	public void delete(@Param("id")Integer id);
	
	/*比較を作成*/
	public Integer selectByAccountId(@Param("id") Integer id);
	
}