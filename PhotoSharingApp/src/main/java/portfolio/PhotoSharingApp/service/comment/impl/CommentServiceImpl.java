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
	
	/*グループID,アカウントID,テキスト入力情報 を追加*/
	@Override
	public void insertCommentData(Comments comments) {
		commentMapper.insertCommentData(comments);
	}
	
	/*id番号,アカウント名,日時,コメント を取得*/
	@Override
	public List<Comments> commentDataList(){
		return commentMapper.selectCommentData();
	}
	
	/*コメントの削除*/
	@Override
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}

}
