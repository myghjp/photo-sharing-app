package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ListMembersController {
	
	@GetMapping("/list-members")
	public String getListMembers(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "members/list-members";
	}
	
	@PostMapping("/list-members")
	public String postListMembers(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:list-members";
	}
}
