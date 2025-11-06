package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMembersController {
	
	@GetMapping("/add-members")
	public String getAddMembers(Model model) {
		
		/*JOIN?*/
		
		return "members/add-members";
	}
	
	

}
