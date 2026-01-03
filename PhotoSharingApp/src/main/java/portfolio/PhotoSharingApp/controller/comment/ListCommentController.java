package portfolio.PhotoSharingApp.controller.comment;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.comment.ListCommentForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.comment.CommentService;

@Controller
@SessionAttributes(value = {"group"})
public class ListCommentController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list-comment")
	public String getListComment(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,ListCommentForm listCommentForm
			,@ModelAttribute("group")Group group
			) {
		
		model.addAttribute("listCommentForm",listCommentForm);
		
		/*↓?*/	
		model.addAttribute("myUsername",loginUserDetails.getUsername());
		
		/*このグループのcommentsテーブル情報とアカウント名を取得*/
		List<Comment> commentList = commentService.getCommentList(group.getId());
		model.addAttribute("commentList", commentList);
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("group")Group group
			,@ModelAttribute @Validated ListCommentForm listCommentForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		if (bindingResult.hasErrors()) {
			return getListComment(model,loginUserDetails,listCommentForm,group);
		}
		
		Comment comment = modelMapper.map(listCommentForm, Comment.class);
		
		comment.setGroupId(group.getId());
		comment.setAccountId(loginUserDetails.getUserId());
		
		commentService.addComment(comment);
	
		return "redirect:list-comment";
	}
}