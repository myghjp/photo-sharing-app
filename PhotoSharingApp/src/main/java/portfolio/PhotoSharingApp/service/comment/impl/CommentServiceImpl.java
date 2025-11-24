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
	
	/*コメント情報を追加*/
	@Override
	public void addComment(Comments comments) {
		commentMapper.insertComment(comments);
	}
	
	/*コメント情報を取得*/
	@Override
	public List<Comments> commentList(){
		return commentMapper.selectComment();
	}
	
	/*コメントを取得*/
	public Comments getComment(int id) {
		return commentMapper.select(id);
	}
	
	/*コメントの削除*/
	@Override
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}

}
