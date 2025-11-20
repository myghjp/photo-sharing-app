package portfolio.PhotoSharingApp.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
@SessionAttributes(value = {"groups"})
public class DeleteGroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/delete-group")
	public String getDeleteGroup(Model model
			/*,Groups groups*/
			) {
		
		return "group/delete-group";
	}
	
	@PostMapping("/delete-group")
	public String postDeleteGroup(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes) {
		
		/*グループの削除*/
		groupService.deleteGroup(id);
		
		return "redirect:select-group";
	}
}