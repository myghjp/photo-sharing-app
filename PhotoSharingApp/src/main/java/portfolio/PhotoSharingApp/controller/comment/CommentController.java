package portfolio.PhotoSharingApp.controller.comment;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.comment.ListCommentForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.CommentService;

@Controller
@SessionAttributes(value = {"group"})
public class CommentController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list-comment")
	public String getListComment(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("listCommentForm")ListCommentForm form
			,@ModelAttribute("group")Group group
			) {
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		model.addAttribute("loginUser",user.getUsername());
		
		/*グループのコメント情報を取得*/
		List<Comment> commentList = commentService.getGroupCommentInfo(group.getId());
		model.addAttribute("commentList", commentList);
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("listCommentForm") @Validated ListCommentForm form
			,BindingResult bindingResult
			,@ModelAttribute("group")Group group
			) {
		
		if (bindingResult.hasErrors()) {
			return getListComment(model,user,form,group);
		}
		
		Comment comment = modelMapper.map(form, Comment.class);
		
		comment.setGroupId(group.getId());
		comment.setAccountId(user.getUserId());
		
		commentService.add(comment);
	
		return "redirect:list-comment";
	}
	
	@PostMapping("/delete-comment")
	public String postDeleteComment(
			@RequestParam("id") int commentId
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		/*投稿者か*/
		if (commentService.isCommenter(commentId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}

		commentService.delete(commentId);

		return "redirect:list-comment";
	}
}