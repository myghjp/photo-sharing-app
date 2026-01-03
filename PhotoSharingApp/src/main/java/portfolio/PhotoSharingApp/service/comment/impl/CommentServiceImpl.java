package portfolio.PhotoSharingApp.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.repository.CommentMapper;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;
	
	/*ーーーListCommentーーー*/
	
	@Override
	public List<Comment> getCommentList(int id){
		return commentMapper.selectCommentItems(id);
	}
	
	@Override
	public void addComment(Comment comment) {
		commentMapper.insert(comment);
	}
	
	/*ーーーDeleteCommentーーー*/
	
	@Override
	public Comment getComment(int id) {
		return commentMapper.select(id);
	}
	
	@Override
	public void deleteComment(int id) {
		commentMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public int isCurrentUser(int id) {
		return commentMapper.selectUserId(id);
	}
}