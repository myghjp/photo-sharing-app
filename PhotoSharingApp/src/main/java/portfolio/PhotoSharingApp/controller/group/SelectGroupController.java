package portfolio.PhotoSharingApp.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
@SessionAttributes(value = {"group"})
public class SelectGroupController {

	@Autowired
	private GroupService groupService;

	@ModelAttribute(value = "group")
	public Group group() {
		return new Group();
	}

	@GetMapping("/select-group")
	public String getSelectGroup(
			Model model
			,SessionStatus sessionStatus
			,@AuthenticationPrincipal LoginUserDetails user
			) {

		sessionStatus.setComplete();
		
		/*自身が所属しているグループIDとグループ名を取得(グループ管理者IDも追加)*/
		List<Group> groupList = groupService.findAllByUserId(user.getUserId());
		model.addAttribute("groupList", groupList);
		
		/*ーーーーーーーーーーーーーーーーーーーーーー*/
		/*自身がグループの管理者であるかを確認*/
		/*if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}*/
		/*ーーーーーーーーーーーーーーーーーーーーーー*/
		
		model.addAttribute("userId",user.getUserId());
		
		boolean isActive = true;
	    model.addAttribute("isActiveSelectGroup", isActive);

		return "group/select-group";
	}

	@PostMapping("/select-group")
	public String postSelectGroup(
			Model model
			,@RequestParam("id")int groupId
			,@AuthenticationPrincipal LoginUserDetails user
			,RedirectAttributes redirectAttributes
			) {

		Group group = groupService.findById(groupId);
		model.addAttribute("group",group);
		
		return "redirect:home-group";
	}
	
	@PostMapping("/delete-group")
	public String postDeleteGroup(
			@RequestParam("id") int groupId
			,RedirectAttributes redirectAttributes) {

		groupService.delete(groupId);

		return "redirect:select-group";
	}
}