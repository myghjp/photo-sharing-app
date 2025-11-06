package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddMembersController {
	
	@GetMapping("/add-members")
	public String getAddMembers(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "members/add-members";
	}

}
