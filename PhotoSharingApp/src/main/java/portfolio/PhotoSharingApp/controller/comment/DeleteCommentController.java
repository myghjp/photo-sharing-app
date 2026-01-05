package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Controller
public class DeleteCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/delete-comment/{id}")
	public String getDeleteComment(Model model
			,@PathVariable("id")int id
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		Comment comment = new Comment();
		comment.setId(id);
		
		/*コメント内容が自身が登録したものかを確認*/
		if (commentService.isCurrentAccount(comment.getId(),loginUserDetails.getAccountId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		/*idとコメントを取得*/
		Comment commentData = commentService.getComment(comment.getId());
		model.addAttribute("commentData", commentData);
		
		return "comment/delete-comment";
	}
	
	@PostMapping("/delete-comment")
	public String getDeleteComment(
			@RequestParam("id") int id
			,RedirectAttributes redirectAttributes
			) {
		
		commentService.deleteComment(id);
	
		return "redirect:list-comment";
	}
}