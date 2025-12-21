package portfolio.PhotoSharingApp.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
@SessionAttributes(value = {"groups"})
/*↑@PathVariable後は不要状態※*/
public class DeleteGroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/delete-group/{id}")
	public String getDeleteGroup(Model model
			,@PathVariable("id")int id
			) {
		
		Groups groupsData = groupService.getGroupsData(id);
		model.addAttribute("groupsData",groupsData);
		
		return "group/delete-group";
	}
	
	@PostMapping("/delete-group")
	public String postDeleteGroup(
			@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		groupService.deleteGroup(id);
		
		return "redirect:select-group";
	}
}