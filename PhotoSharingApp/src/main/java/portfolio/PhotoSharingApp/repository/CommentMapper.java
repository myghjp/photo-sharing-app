package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comment;

@Mapper
public interface CommentMapper {
	
	public void insert(@Param("comment") Comment comment);
	
	public void delete(Integer commentId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public List<Comment> selectComments(Integer groupId);
	
	public Comment selectComment(Integer commentId);
	
	public Integer selectByAccountId(Integer commentId);
}