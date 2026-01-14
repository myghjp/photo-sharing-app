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
	
	@Override
	public void add(Comment comment) {
		commentMapper.insert(comment);
	}
	
	@Override
	public List<Comment> findAllById(int groupId){
		return commentMapper.selectComments(groupId);
	}
	
	@Override
	public void delete(int commentId) {
		commentMapper.delete(commentId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Comment findById(int commentId) {
		return commentMapper.selectComment(commentId);
	}
	
	@Override
	public boolean isComment(int commentId,int userId) {
		if (commentMapper.selectByAccountId(commentId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}