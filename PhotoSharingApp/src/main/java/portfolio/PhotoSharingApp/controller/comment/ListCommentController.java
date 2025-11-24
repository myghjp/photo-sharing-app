package portfolio.PhotoSharingApp.controller.comment;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Comments;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.form.comment.ListCommentForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Controller
@SessionAttributes(value = {"groups"})
public class ListCommentController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list-comment")
	public String getListComment(Model model
			,Groups groups
			,ListCommentForm listCommentForm
			) {
		
		model.addAttribute("listCommentForm",listCommentForm);
		model.addAttribute("groupName",groups.getGroupName());
		
		/*id番号,アカウント名,コメントした日時,入力されたテキストを取得*/
		List<Comments> commentList = commentService.commentList();
		model.addAttribute("commentList", commentList);
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment(Model model
			,Groups groups
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute ListCommentForm listCommentForm
			,RedirectAttributes redirectAttributes
			) {
		/*return無し*/
		
		Comments comments = modelMapper.map(listCommentForm, Comments.class);
		
		comments.setGroupId(groups.getId());
		comments.setAccountId(loginUserDetails.getUserId());
		
		commentService.addComment(comments);
	
		return "redirect:list-comment";
	}
}