package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Comments;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Controller
public class DeleteCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/delete-comment/{id}")
	public String getDeleteComment(Model model
			,@PathVariable("id")int commentId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*※コメント内容が自身が登録したものかを確認*/
		Comments comments = new Comments();
		comments.setId(commentId);
		
		if (commentService.isCurrentUser(comments.getId()) != loginUserDetails.getUserId()) {
			throw new IllegalArgumentException("不正なIDです");
		}
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		/*idとコメントを取得*/
		Comments commentsData = commentService.getComment(comments.getId());
		model.addAttribute("commentsData", commentsData);
		
		return "comment/delete-comment";
	}
	
	@PostMapping("/delete-comment")
	public String getDeleteComment(
			@RequestParam("id") int commentId
			,RedirectAttributes redirectAttributes
			) {
		
		
	
		commentService.deleteComment(commentId);
	
		return "redirect:list-comment";
	}
}