package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListMembersController {
	
	@GetMapping("/list-members")
	public String getListMembers() {
		
		return "members/list-members";
	}

	@PostMapping("/list-members")
	public String postListMembers() {
		return "redirect:list-members";
	}
}
