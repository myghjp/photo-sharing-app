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
			,@PathVariable("id")int commentId
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		Comment comment = new Comment();
		comment.setId(commentId);
		
		/*このコメントは自身がコメントしたものかを確認*/
		if (commentService.isMyComment(comment.getId(),user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		/*コメントのIDとコメント内容を取得*/
		Comment commentData = commentService.findById(comment.getId());
		model.addAttribute("commentData", commentData);
		
		return "comment/delete-comment";
	}
	
	@PostMapping("/delete-comment")
	public String getDeleteComment(
			@RequestParam("id") int id
			,RedirectAttributes redirectAttributes
			) {
		
		commentService.delete(id);
	
		return "redirect:list-comment";
	}
}