package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListMembersController {
	
	@GetMapping("/list-members")
	public String getListMembers() {
		
		return "members/list-members";
	}

}
