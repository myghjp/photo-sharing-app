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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getListComment(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,ListCommentForm form
			,@ModelAttribute("group")Group group
			) {
		
		model.addAttribute("listCommentForm",form);
		model.addAttribute("loginUser",user.getUsername());
		
		/*このグループのコメントのテーブル情報とアカウント名を取得*/
		List<Comment> commentList = commentService.findAllById(group.getId());
		model.addAttribute("commentList", commentList);
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		boolean isActive = true;
	    model.addAttribute("isActiveComment", isActive);
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("listCommentForm") @Validated ListCommentForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
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
	public String getDeleteComment(
			@RequestParam("id") int id
			,RedirectAttributes redirectAttributes
			) {

		commentService.delete(id);

		return "redirect:list-comment";
	}
}