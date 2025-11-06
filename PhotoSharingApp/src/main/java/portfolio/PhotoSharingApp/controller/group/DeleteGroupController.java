package portfolio.PhotoSharingApp.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class DeleteGroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/delete-group")
	public String getDeleteGroup(Model model
			,RedirectAttributes redirectAttributes
			) {
		return "group/delete-group";
	}
	
	@PostMapping("/delete-group")
	public String postHomeGroupDelete(
			@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		Groups groups = groupService.getGroupsInfo(id);
		
		redirectAttributes.addFlashAttribute("groups",groups);
		
		return "redirect:delete-group";
	}
	
	@PostMapping("/delete-group2")
	public String postDeleteGroup(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes) {
		
		/*グループの削除*/
		groupService.deleteGroup(id);
		
		return "redirect:select-group";
	}

}
