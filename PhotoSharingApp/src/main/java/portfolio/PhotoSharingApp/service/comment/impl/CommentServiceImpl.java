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
	
	/*ーListCommentーーーーーーーーーーーーーーーーー*/
	
	/*commentsテーブルの情報とアカウント名を取得*/
	@Override
	public List<Comments> getCommentList(){
		return commentMapper.selectCommentList();
	}
	
	/*コメント情報を追加*/
	@Override
	public void addComment(Comments comments) {
		commentMapper.insertComment(comments);
	}
	
	
	/*ーDeleteCommentーーーーーーーーーーーーーーーーー*/
	
	/*このidとコメントを取得*/
	@Override
	public Comments getComment(int id) {
		return commentMapper.select(id);
	}
	
	/*コメントの削除*/
	@Override
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}

}
