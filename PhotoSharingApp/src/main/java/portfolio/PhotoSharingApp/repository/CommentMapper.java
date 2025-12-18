package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comments;

@Mapper
public interface CommentMapper {
	
	/*ーーーListCommentーーー*/

	public List<Comments> selectCommentItems(@Param("id")Integer id);
	
	public void insert(@Param("comments") Comments comments);
	
	/*ーーーDeleteCommentーーー*/
	
	public Comments select(@Param("id")Integer id);

	public void delete(@Param("id")Integer id);
	
}