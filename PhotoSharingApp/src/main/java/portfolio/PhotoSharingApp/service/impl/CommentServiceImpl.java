package portfolio.PhotoSharingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.repository.CommentMapper;
import portfolio.PhotoSharingApp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void add(Comment comment) {
		commentMapper.insert(comment);
	}
	
	@Override
	public List<Comment> getGroupCommentInfo(int groupId){
		return commentMapper.selectByGroupId(groupId);
	}
	
	@Override
	public void delete(int commentId) {
		commentMapper.delete(commentId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Comment findById(int commentId) {
		return commentMapper.selectByCommentId(commentId);
	}
	
	@Override
	public boolean isCommenter(int commentId,int userId) {
		if (commentMapper.selectAccountIdByCommentId(commentId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}