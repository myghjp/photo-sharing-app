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
	public void addComment(Comment comment) {
		commentMapper.insert(comment);
	}
	
	@Override
	public void deleteComment(int id) {
		commentMapper.delete(id);
	}
	
	/*このグループのコメントのテーブル情報とアカウント名を取得*/
	@Override
	public List<Comment> getCommentList(int id){
		return commentMapper.selectCommentItems(id);
	}
	
	/*コメントのIDとコメント内容を取得*/
	@Override
	public Comment getComment(int id) {
		return commentMapper.selectById(id);
	}
	
	/*このコメントは自身がコメントしたものかを確認*/
	@Override
	public boolean isFindAddMyComment(int commentId,int loginId) {
		if ( commentMapper.selectByAccountId(commentId) == loginId) {
			return false;
		} else {
			return true;
		}
	}
}