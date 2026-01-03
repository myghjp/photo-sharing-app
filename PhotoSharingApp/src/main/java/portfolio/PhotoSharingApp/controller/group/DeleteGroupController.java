package portfolio.PhotoSharingApp.controller.group;

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

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class DeleteGroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/delete-group/{id}")
	public String getDeleteGroup(Model model
			,@PathVariable("id")int groupId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		/*※このグループidは自身のアカウントIdが作成したかを確認*/
		if (groupService.isCurrentUser(groupId) != loginUserDetails.getUserId()) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		Group groupData = groupService.getGroupsData(groupId);
		model.addAttribute("groupData",groupData);
		
		return "group/delete-group";
	}
	
	@PostMapping("/delete-group")
	public String postDeleteGroup(
			@RequestParam("id")int groupId
			,RedirectAttributes redirectAttributes
			) {
		
		groupService.deleteGroup(groupId);
		
		return "redirect:select-group";
	}
}