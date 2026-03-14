package portfolio.PhotoSharingApp.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Comment;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.ListCommentForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.CommentService;

@Controller
@RequestMapping("/comment")
@SessionAttributes(value = {"group"})
public class CommentController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list")
	public String getList(
			Model model
			,@ModelAttribute("listCommentForm")ListCommentForm form
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
		) {
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*グループのコメント情報を取得*/
		List<Comment> commentList = commentService.getGroupCommentInfo(group.getId());
		model.addAttribute("commentList", commentList);
		
		return "comment/list";
	}
	
	@PostMapping("/list")
	public String postList(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("listCommentForm")@Validated ListCommentForm form
			,BindingResult bindingResult
			,@ModelAttribute("group")Group group
		) {
		
		if (bindingResult.hasErrors()) {
			return getList(model,form,user,group);
		}
		
		Comment comment = modelMapper.map(form, Comment.class);
		comment.setGroupId(group.getId());
		comment.setAccountId(user.getUserId());
		
		commentService.add(comment);
	
		return "redirect:list";
	}
	
	@PostMapping("/delete")
	public String postDelete(
			@RequestParam("id") int commentId
			,@AuthenticationPrincipal LoginUserDetails user
		) {
		
		/*投稿者か*/
		if (commentService.isCommenter(commentId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}

		commentService.delete(commentId);

		return "redirect:list";
	}
}