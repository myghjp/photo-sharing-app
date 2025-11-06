package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeleteMembersController {
	
	@GetMapping("/delete-members")
	public String getDeleteMembers(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "members/delete-members";
	}

}
