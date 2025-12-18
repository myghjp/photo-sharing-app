package portfolio.PhotoSharingApp.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Comments;
import portfolio.PhotoSharingApp.repository.CommentMapper;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;
	
	/*ーーーListCommentーーー*/
	
	@Override
	public List<Comments> getCommentList(int id){
		return commentMapper.selectCommentItems(id);
	}
	
	@Override
	public void addComment(Comments comments) {
		commentMapper.insert(comments);
	}
	
	/*ーーーDeleteCommentーーー*/
	
	@Override
	public Comments getComment(int id) {
		return commentMapper.select(id);
	}
	
	@Override
	public void deleteComment(int id) {
		commentMapper.delete(id);
	}
}