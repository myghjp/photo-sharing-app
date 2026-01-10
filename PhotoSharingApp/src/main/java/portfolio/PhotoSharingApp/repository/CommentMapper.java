package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Comment;

@Mapper
public interface CommentMapper {
	
	public void insert(@Param("comment") Comment comment);
	
	public void delete(Integer id);
	
	public List<Comment> selectCommentItems(Integer id);
	
	public Comment selectById(Integer id);
	
	public Integer selectByAccountId(Integer id);
}